package com.EdtAppProject.edtApp.dto;

import com.EdtAppProject.edtApp.entite.Enum.ETypeEnseignant;
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

    private ETypeEnseignant typeEnseignant;

    private String grade;

    private String specialite;

//    @NotBlank(message = "Identifiant du module obligatoire !")
//    private String idModule;

}
