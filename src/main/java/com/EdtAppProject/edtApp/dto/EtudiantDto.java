package com.EdtAppProject.edtApp.dto;

import com.EdtAppProject.edtApp.entite.Enum.ETitreEtudiant;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDto extends UtilisateurDto {

    @NotBlank(message = "INE de l'étudiant obligatoire !")
    private String ine;

    @NotBlank(message = "Titre de l'étudiant obligatoire !")
    private ETitreEtudiant titreEtudiant;

    @NotBlank(message = "Identifiant du parent obligatoire !")
    private String idParent;

    @NotBlank(message = "Identifiant de la filière obligatoire !")
    private String idFiliere;
}
