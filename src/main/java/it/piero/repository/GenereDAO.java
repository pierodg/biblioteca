package it.piero.repository;

import model.Genere;

import java.util.List;

public interface GenereDAO {

    public List<Genere> getGenereList();
    public Genere getGenere(int id);

}
