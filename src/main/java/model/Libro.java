package model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Libro {
    private int id;
    private String titolo;
    private String editore;
    private int annoPubblicazione;
    private Genere genere;
    private List<Scrittore> autore;

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", editore='" + editore + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", genere=" + genere +
                ", autore=" + autore +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(this instanceof Libro)) return false;
        Libro libro = (Libro) obj;
        return this.id == libro.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}