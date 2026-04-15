package solis3d.u5w2d2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;

@Entity
@Table(name = "blog_posts")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String categoria;
    @Column(nullable = false)
    private String titolo;
    @Column(nullable = false)
    private String cover;
    @Column(nullable = false)
    private String contenuto;
    @Column(nullable = false)
    private int tempoDiLettura;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public BlogPost(String categoria, String titolo, String contenuto, int tempoDiLettura, Author author) {
        Random random=new Random();
        this.id = random.nextLong(1,10000);
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = "https://picsum.photos/200/300";
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.author = author;
    }

}
