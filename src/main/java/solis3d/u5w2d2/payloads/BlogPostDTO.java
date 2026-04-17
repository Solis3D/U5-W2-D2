package solis3d.u5w2d2.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;

public record BlogPostDTO(

        @NotBlank(message = "La categoria è obbligatoria")
        @Size(min = 2, max = 30, message = "La categoria deve essere compresa tra i 2 e i 30 caratteri")
        String categoria,

        @NotBlank(message = "Il titolo è obbligatorio")
        @Size(min =2, max = 60, message = "Il titolo deve essere compreso tra i 2 e i 30 caratteri")
        String titolo,

        @NotBlank(message = "Il contenuto è obbligatorio")
        @Size(min = 10, message = "Il contenuto deve avere almeno 10 caratteri!")
        String contenuto,

        @NotNull(message = "Il tempo di lettura è obbligatorio")
        @Min(value = 1, message = "Il tempo di lettura deve essere di almeno 1 minuto")
        Integer tempoDiLettura,

        @NotNull(message = "L'id dell'autore è obbligatorio")
        Long authorId
) {
}
