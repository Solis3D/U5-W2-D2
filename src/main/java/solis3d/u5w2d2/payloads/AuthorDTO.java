package solis3d.u5w2d2.payloads;

import jakarta.validation.constraints.*;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDate;

public record AuthorDTO(
        @NotBlank (message = "Il nome è obbligatorio, non può essere vuoto!")
        @Size(min = 2, max = 30, message = "Il nome deve essere compreso tra i 2 e i 30 caratteri")
        String nome,

        @NotNull(message = "Il cognome è obbligatorio, non può essere vuoto!")
        @Size(min = 2, max = 30, message = "Il cognome deve essere compreso tra i 2 e i 30 caratteri")
        String cognome,

        @NotBlank(message = "L'email è obbligatoria, non può essere vuota!")
        @Email(message = "L'email inserita non è in un formato valido")
        String email,

        @NotNull(message = "La data di nascita è obbligatoria")
        @Past(message = "La data di nascita deve essere nel passato")
        LocalDate dataDiNascita
        ) {

}
