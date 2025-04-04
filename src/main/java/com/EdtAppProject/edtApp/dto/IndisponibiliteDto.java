package com.EdtAppProject.edtApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndisponibiliteDto {

    private String id;

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;

    @NotBlank(message = "Motif d'indisponibilité obligatoire !")
    private String modif;

    @NotBlank(message = "Identifiant de l'enseignant obligatoire !")
    private String idEnseignant;
}
