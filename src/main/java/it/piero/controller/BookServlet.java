package it.piero.controller;

import com.google.gson.Gson;
import it.piero.repository.*;
import model.Genere;
import model.Libro;
import model.Scrittore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(value = "/book")
public class BookServlet extends HttpServlet {
    private LibroDAO libroDAO;
    private GenereDAO genereDAO;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Override
    public void init() throws ServletException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        libroDAO = new LibroDAOImpl();
        genereDAO = new GenereDAOImpl();

        switch (action) {
            case "showBooks":{
                Set<Libro> libri = libroDAO.retrieveBooks();
                Map<Integer,String> mapAutori = new HashMap<>();
                List<Genere> generi;
                System.out.println(libri);

                int i;
                for (Libro libro : libri) {
                    String scrittori = "";
                    i = 0;
                    List<Scrittore> autori = libro.getAutore();
                    for(Scrittore scrittore : autori) {
                        if(i != autori.size() - 1)
                            scrittori += scrittore.getNome() + " " + scrittore.getCognome() + ", ";
                        else
                            scrittori += scrittore.getNome() + " " + scrittore.getCognome();
                        i++;
                    }
                    mapAutori.put(libro.getId(),scrittori);
                }
                generi = genereDAO.getGenereList();

                request.setAttribute("generi", generi);
                request.setAttribute("libri", libri);
                request.setAttribute("autori", mapAutori);
                request.getRequestDispatcher("/view/addbook.jsp").forward(request,response);
                break;
            }
            case "modifica":{
                int idLibro = Integer.parseInt(request.getParameter("idLibro"));
                Libro libro = libroDAO.getBookById(idLibro);
                System.out.println(ANSI_GREEN + libro + ANSI_RESET);

                String json = new Gson().toJson(libro);
                System.out.println(json);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);

                break;
            }
            case "elimina": {
                int idLibro = Integer.parseInt(request.getParameter("idLibro"));
                Libro libro = new Libro();
                libro.setId(idLibro);
                libroDAO.delete(libro);

                String json = new Gson().toJson(libro);
                System.out.println(json);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);

                break;
            }

            case "saveOrUpdate": {
                Libro libro = new Libro();
                Scrittore scrittore = new Scrittore();
                Scrittore scrittore2 = new Scrittore();
                List<Scrittore> scrittori = new ArrayList<>();

                Genere genere = genereDAO.getGenere(6);

                scrittore.setId(8);
                scrittore.setNome("Giuseppe");
                scrittore.setCognome("Fornaro");

                scrittore2.setId(9);
                scrittore2.setNome("Pietro");
                scrittore2.setCognome("Di Giovanni");

                scrittori.add(scrittore);
                scrittori.add(scrittore2);

                libro.setId(3);
                libro.setTitolo("I tre finanzieri");
                libro.setAnnoPubblicazione(2020);
                libro.setEditore("Morrison");
                libro.setGenere(genere);
                libro.setAutore(scrittori);

                libroDAO.saveOrUpdate(libro);
                String json = new Gson().toJson(libro);
                System.out.println(json);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            }
        }
    }
}
