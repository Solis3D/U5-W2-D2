package solis3d.u5w2d2.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import solis3d.u5w2d2.entities.Author;
import solis3d.u5w2d2.exceptions.BadRequestException;
import solis3d.u5w2d2.exceptions.NotFoundException;
import solis3d.u5w2d2.payloads.AuthorDTO;
import solis3d.u5w2d2.payloads.NewAuthorPayload;
import solis3d.u5w2d2.repositories.AuthorsRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AuthorService {

    private final AuthorsRepository authorsRepository;
    private final Cloudinary cloudinaryUploader;

    public AuthorService(AuthorsRepository authorsRepository, Cloudinary cloudinaryUploader) {
        this.authorsRepository = authorsRepository;
        this.cloudinaryUploader = cloudinaryUploader;
    }

    public Author saveAuthor(AuthorDTO body){
        if (this.authorsRepository.existsByEmail(body.email())){
            throw new BadRequestException("L'email " + body.email() + " è già in uso!");
        }

        Author newAuthor = new Author(body.nome(), body.cognome(), body.email(), body.dataDiNascita());

        Author savedauthor = authorsRepository.save(newAuthor);

        log.info("Autore con id " + newAuthor.getId() + " salvato con successo!");
        return savedauthor;
    }

    public List<Author> findAll() {
        return this.authorsRepository.findAll();
    }

    public Author findById(long authorId){
        return  this.authorsRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public Author findByIdAndUpdate(long authorId, NewAuthorPayload body){
        Author trovato = this.findById(authorId);

        if(!trovato.getEmail().equals(body.getEmail())){
            if (this.authorsRepository.existsByEmail(body.getEmail())) {
                throw new BadRequestException("L'email " + body.getEmail() + " è già in uso!");
            }
        }

        trovato.setNome(body.getNome());
        trovato.setCognome(body.getCognome());
        trovato.setEmail(body.getEmail());
        trovato.setDataDiNascita(body.getDataDiNascita());
        trovato.setAvatar("https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome());

        Author updatedAuthor = authorsRepository.save(trovato);
        log.info("Autore con id " + updatedAuthor.getId() + " aggiornato con successo!");
        return updatedAuthor;
    }

    public void findByIdAndDelete(long authorId) {
        Author found = this.findById(authorId);
        this.authorsRepository.delete(found);
    }

    public Author uploadAvatar(MultipartFile file, long authorId){

        Author trovato = this.findById(authorId);

        if(file.isEmpty()){
            throw new BadRequestException("Il file è obbligatorio!");
        }

        try{
            Map result = cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("secure_url");

            trovato.setAvatar(url);
            return this.authorsRepository.save(trovato);

        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
