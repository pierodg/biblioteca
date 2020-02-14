package model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Scrittore {
    private int id;
    private String nome;
    private String cognome;
    private List<Libro> libro;

    @Override
    public String toString() {
        return "Scrittore{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", libro=" + libro +
                '}';
    }
}
