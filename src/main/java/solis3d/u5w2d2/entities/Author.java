package solis3d.u5w2d2.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Random;

@Getter
@Setter
@ToString
public class Author {
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;

    public Author (String nome, String cognome, String email, LocalDate dataDiNascita){
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
        Random random = new Random();
        this.id = random.nextLong(1,10000);
        this.avatar = "https://ui-avatars.com/api/?name=" + nome + "+" + cognome;    }
}
