package solis3d.u5w2d2.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import solis3d.u5w2d2.entities.Author;
import solis3d.u5w2d2.entities.BlogPost;
import solis3d.u5w2d2.exceptions.NotFoundException;
import solis3d.u5w2d2.payloads.NewAuthorPayload;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthorService {
    private List<Author> authorsDB = new ArrayList<>();

    public List<Author> findAll(){return authorsDB;}

    public Author saveAuthor(NewAuthorPayload body){
        Author newAuthor = new Author(body.getNome(), body.getCognome(), body.getEmail(), body.getDataDiNascita());
        this.authorsDB.add(newAuthor);
        log.info("Autore con id " + newAuthor.getId() + " salvato con successo!");
        return newAuthor;
    }

    public Author findById(long authorId){
        Author trovato = null;

        for (Author author : authorsDB) {
            if(author.getId() == authorId){trovato = author;}
        }

        if(trovato == null){throw new NotFoundException(authorId);}
        return trovato;
    }

    public Author findByIdAndUpdate(long authorId, NewAuthorPayload body){
        Author trovato = null;

        for (Author author : authorsDB) {
            if(author.getId() == authorId){
                trovato = author;
                trovato.setNome(body.getNome());
                trovato.setCognome(body.getCognome());
                trovato.setEmail(body.getEmail());
                trovato.setDataDiNascita(body.getDataDiNascita());
            }
        }

        if (trovato == null){throw new NotFoundException(authorId);}
        return trovato;
    }

    public void findByIdAndDelete(long authorId){
        Author trovato = null;
        for (Author author : authorsDB) {
            if(author.getId() == authorId){trovato = author;}
        }
        if(trovato == null){throw new NotFoundException(authorId);}
        authorsDB.remove(trovato);
    }
}
