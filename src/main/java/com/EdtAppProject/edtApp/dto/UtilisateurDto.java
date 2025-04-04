package com.EdtAppProject.edtApp.dto;

import com.EdtAppProject.edtApp.entite.Enum.ESexe;
import com.EdtAppProject.edtApp.entite.Enum.EStatutCompte;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto {

    private String id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prenom est obligatoire")
    private String prenom;

    @NotBlank(message = "Le sexe est obligatoire")
    private ESexe sexe;

    @Pattern(
            regexp = "^(\\+\\d{1,3})?\\s?(\\d{8})$",
            message = "Le numéro de téléphone est invalide."
    )
    @NotBlank(message = " Le numéro de telephone est obligatoire ")
    private String telephone;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String password;

    private EStatutCompte statutCompte;
}
