package it.piero.repository;

import model.Scrittore;

import java.util.List;

public interface ScrittoreDAO {
    public List<Scrittore> getAutori(int id);

}
