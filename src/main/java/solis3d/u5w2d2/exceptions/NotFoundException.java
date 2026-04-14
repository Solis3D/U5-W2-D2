package solis3d.u5w2d2.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("L'elemento ocn id " + id + " non è stato trovato!");
    }

}
