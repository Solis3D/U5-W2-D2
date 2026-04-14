package solis3d.u5w2d2.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class NewAuthorPayload {
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
}
