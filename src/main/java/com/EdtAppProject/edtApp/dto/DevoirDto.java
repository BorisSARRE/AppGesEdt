package com.EdtAppProject.edtApp.dto;


import com.EdtAppProject.edtApp.entite.Enum.ESatutDevoir;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DevoirDto {

    private String id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private float duree;

    private ESatutDevoir statutDevoir;

    @NotBlank(message = "Identifiant de la salle obligatoire !")
    private String idSalle;

    @NotBlank(message = "Identifiant du module obligatoire !")
    private String idMatiere;

    @NotBlank(message = "Identifiant de l'emploi du temps obligatoire !")
    private String idEmploiDuTemps;

}
