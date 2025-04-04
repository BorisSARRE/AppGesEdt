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
public class NotificationDto {

    private String id;

    @NotBlank(message = "Titre  obligatoire !")
    private String titre;

    @NotBlank(message = "Contenu obligatoire !")
    private String contenu;

    @NotBlank(message = "Identifiant du cours obligatoire !")
    private String idCours;
}
