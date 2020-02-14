package it.piero.repository;

import model.Genere;
import model.Libro;
import model.Scrittore;

import java.util.List;
import java.util.Set;

public interface LibroDAO {
    public Set<Libro> retrieveBooks();
    public List<Scrittore> getAutori(int id);

    public void saveOrUpdate(Libro libro);

    public void delete(Libro libro);

    public Libro getBookById(int id);
    public Libro getBookByAutore(int autore);
    public Libro getBookByTitolo(String titolo);
    public Libro getBookByEditore(String editore);
    public Libro getBookByAnno(int annoPubblicazione);
    public Libro getBookByGenere(int genere);


}
