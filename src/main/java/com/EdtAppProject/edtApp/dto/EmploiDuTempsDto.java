package com.EdtAppProject.edtApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmploiDuTempsDto {

    private String id;

    @NotNull
    private LocalDateTime datePublication;

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;

    @NotBlank(message = "Identifiant du cours obligatoire !")
    private String idCours;

    @NotBlank(message = "Identifiant de la filière obligatoire !")
    private String idFiliere;

}
