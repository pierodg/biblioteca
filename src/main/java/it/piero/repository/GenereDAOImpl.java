package it.piero.repository;

import model.Genere;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenereDAOImpl extends GenericDAO implements GenereDAO {

    public GenereDAOImpl() {
        super();
    }

    @Override
    public List<Genere> getGenereList() {
        List<Genere> genereList = new ArrayList<>();
        PreparedStatement statement;
        String query = "SELECT * FROM genere";

        try {
            statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Genere genere = new Genere();
                genere.setId(result.getInt("id"));
                genere.setNome(result.getString("nome"));

                genereList.add(genere);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genereList;
    }

    @Override
    public Genere getGenere(int id) {
        Genere genere = new Genere();
        PreparedStatement statement;
        String query = "SELECT * FROM genere WHERE id = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                genere.setId(result.getInt("id"));
                genere.setNome(result.getString("nome"));

                return genere;
            }

        } catch (SQLException e) {
        e.printStackTrace();
        }
        return genere;
    }
}
