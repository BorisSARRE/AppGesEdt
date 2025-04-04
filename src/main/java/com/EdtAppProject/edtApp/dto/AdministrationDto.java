package com.EdtAppProject.edtApp.dto;

import com.EdtAppProject.edtApp.entite.Enum.ETypeAdmin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AdministrationDto extends UtilisateurDto {

    @NotBlank(message = "Le matricule est obligatoire")
    private String matricule;

    @NotBlank(message = "Le type d'admin est obligatoire")
    private ETypeAdmin typeAdmin;
}
