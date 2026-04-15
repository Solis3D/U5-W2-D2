package solis3d.u5w2d2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Random;

@Entity
@Table(name = "authors")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private LocalDate dataDiNascita;
    @Column(nullable = false)
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
