package com.EdtAppProject.edtApp.dto;

import com.EdtAppProject.edtApp.entite.Enum.ERole;
import com.EdtAppProject.edtApp.entite.Enum.ESexe;
import com.EdtAppProject.edtApp.entite.Enum.ETitreEtudiant;
import com.EdtAppProject.edtApp.entite.Enum.ETypeAdmin;
import com.EdtAppProject.edtApp.entite.Enum.ETypeEnseignant;
import com.EdtAppProject.edtApp.entite.Enum.ETypeParent;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotNull(message = "Le sexe est obligatoire")
    private ESexe sexe;

    @Pattern(
            regexp = "^(\\+\\d{1,3})?\\s?(\\d{8})$",
            message = "Le numéro de téléphone est invalide."
    )
    @NotBlank(message = " Le numéro de telephone est obligatoire ")
    private String telephone;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String password;

    // Champs spécifiques selon le type d'utilisateur
    private ETypeParent typeParent;
    private String lieuResidence;

    private String ine;
    private ETitreEtudiant titreEtudiant;
    private String parentId;
    private String filiereId;

    private String matricule;
    private ETypeEnseignant typeEnseignant;
    private String grade;
    private String specialite;

    private ETypeAdmin typeAdmin;

    @NotNull(message = "Le rôle est obligatoire")
    private ERole role;

}
