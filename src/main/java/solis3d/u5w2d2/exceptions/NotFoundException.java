package solis3d.u5w2d2.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("L'elemento ocn id " + id + " non è stato trovato!");
    }

}
