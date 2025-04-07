package com.EdtAppProject.edtApp.dto;

import com.EdtAppProject.edtApp.entite.Enum.ESemestre;
import com.EdtAppProject.edtApp.entite.Enum.EStatutMatiere;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatiereDto {

    private String id;

    @NotBlank(message = "Intitulé du module obligatoire !")
    private String intitule;

    @NotNull
    private int volumeHoraire;

    @NotNull(message = " Semestre du cours obligatoire !")
    private ESemestre semestre;

    private EStatutMatiere statutMatiere;

    @NotBlank(message = "Identifiant de l'enseignant' obligatoire !")
    private String idEnseignant;

    @NotBlank(message = " Identifiant de la filière obligatoire !")
    private String idFiliere;
}
