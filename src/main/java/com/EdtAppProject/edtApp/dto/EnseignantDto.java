package com.EdtAppProject.edtApp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnseignantDto extends UtilisateurDto {

    @NotBlank(message = "Le matricule est obligatoire")
    private String matricule;

    private String grade;

    @NotBlank(message = "La specialite est obligatoire")
    private String specialite;

    @NotBlank(message = "Identifiant du module obligatoire !")
    private String idModule;

}
