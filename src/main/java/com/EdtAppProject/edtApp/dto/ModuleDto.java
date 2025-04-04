package com.EdtAppProject.edtApp.dto;

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
public class ModuleDto {

    private String id;

    @NotBlank(message = "Intitulé du module obligatoire !")
    private String intitule;

    @NotNull
    private int volumeHoraire;

    @NotBlank(message = "Identifiant de l'enseignant' obligatoire !")
    private String idEnseignant;
}
