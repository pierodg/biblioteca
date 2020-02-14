package it.piero.controller;

import model.Libro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String hello = "Hello Piero";
        req.setAttribute("hello", hello);

        //List<Libro> libri = (List<Libro>) req.getSession().getAttribute("libri");
        //req.setAttribute("libri", libri);

        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
}
