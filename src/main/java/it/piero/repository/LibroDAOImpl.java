package it.piero.repository;

import model.Genere;
import model.Libro;
import model.Scrittore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LibroDAOImpl extends GenericDAO implements LibroDAO {

    public LibroDAOImpl() {
        super();
    }

    @Override
    public Set<Libro> retrieveBooks() {
        Set<Libro> libri = new HashSet<>();
        PreparedStatement statement = null;
        String query = "SELECT * FROM libro l, genere g, scrittore s, autore a WHERE l.genere = g.id AND l.id = a.id_libro AND a.id_scrittore = s.id";
        try {
            statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Libro libro = new Libro();
                Genere genere = new Genere();
                libro.setId(result.getInt(1)); //id Libro
                libro.setTitolo(result.getString("titolo"));
                libro.setEditore(result.getString("editore"));
                libro.setAnnoPubblicazione(result.getInt("anno_pubblicazione"));

                genere.setNome(result.getString(7)); //nome Genere
                libro.setGenere(genere);

                libri.add(libro);
            }

            for(Libro libro : libri) {
                libro.setAutore(getAutori(libro.getId()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return libri;
    }

    @Override
    public List<Scrittore> getAutori(int id) {
        List<Scrittore> scrittori = new ArrayList<>();
        PreparedStatement statement;
        String query = "SELECT s.nome, s.cognome FROM scrittore s, autore a WHERE a.id_scrittore = s.id and a.id_libro = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Scrittore scrittore = new Scrittore();
                scrittore.setNome(resultSet.getString(1));
                scrittore.setCognome(resultSet.getString(2));

                scrittori.add(scrittore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scrittori;
    }

    @Override
    public void saveOrUpdate(Libro libro) {
        PreparedStatement statement = null;

        String queryLibro = "INSERT INTO `biblioteca`.`libro` (`id`, `titolo`, `editore`, `anno_pubblicazione`, `genere`) VALUES (?,?,?,?,?)";
        String queryAutore = "INSERT INTO `biblioteca`.`autore` (`id_libro`, `id_scrittore`) VALUES (?, ?)";

        try {
            statement = connection.prepareStatement(queryLibro);
            statement.setInt(1,libro.getId());
            statement.setString(2,libro.getTitolo());
            statement.setString(3,libro.getEditore());
            statement.setInt(4,libro.getAnnoPubblicazione());
            statement.setInt(5, libro.getGenere().getId());
            statement.executeUpdate();

            String queryCheckAutori = "SELECT * FROM biblioteca.scrittore WHERE id = ?";
            String queryAddAutore = "INSERT INTO `biblioteca`.`scrittore` (`id`, `nome`, `cognome`) VALUES (?, ?, ?)";

            statement = connection.prepareStatement(queryCheckAutori);

            List<Scrittore> autori = libro.getAutore();
            for(Scrittore scrittore : autori) {
                statement.setInt(1,scrittore.getId());
                ResultSet result = statement.executeQuery();
                if(!result.next()) {
                    statement = connection.prepareStatement(queryAddAutore);
                    statement.setInt(1, scrittore.getId());
                    statement.setString(2, scrittore.getNome());
                    statement.setString(3, scrittore.getCognome());
                    statement.executeUpdate();
                }
                statement = connection.prepareStatement(queryAutore);
                statement.setInt(1, libro.getId());
                statement.setInt(2, scrittore.getId());
                statement.executeUpdate();

                statement = connection.prepareStatement(queryCheckAutori);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Libro libro) {
        PreparedStatement statement = null;
        String query = "DELETE FROM autore WHERE id_libro = ?";
        String query2 = "DELETE FROM libro WHERE id = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,libro.getId());
            statement.executeUpdate();

            statement = connection.prepareStatement(query2);
            statement.setInt(1,libro.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Libro getBookById(int id) {
        PreparedStatement statement;
        String query ="SELECT * FROM libro l, genere g, scrittore s, autore a WHERE l.genere = g.id AND l.id = a.id_libro AND a.id_scrittore = s.id AND l.id = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Libro libro = new Libro();
                Genere genere = new Genere();

                libro.setId(result.getInt("id"));
                libro.setTitolo(result.getString("titolo"));
                libro.setEditore(result.getString("editore"));
                libro.setAnnoPubblicazione(result.getInt("anno_pubblicazione"));

                genere.setId(result.getInt(6)); //id Genere
                genere.setNome(result.getString("nome"));//nome Genere
                libro.setGenere(genere);

                libro.setAutore(getAutori(libro.getId()));


                return libro;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Libro getBookByAutore(int autore) {
        return null;
    }

    @Override
    public Libro getBookByTitolo(String titolo) {
        return null;
    }

    @Override
    public Libro getBookByEditore(String editore) {
        return null;
    }

    @Override
    public Libro getBookByAnno(int annoPubblicazione) {
        return null;
    }

    @Override
    public Libro getBookByGenere(int genere) {
        return null;
    }


}
