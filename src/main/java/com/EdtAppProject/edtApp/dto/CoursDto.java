package com.EdtAppProject.edtApp.dto;

import com.EdtAppProject.edtApp.entite.Enum.ECrenau;
import com.EdtAppProject.edtApp.entite.Enum.EDisponibiliteProf;
import com.EdtAppProject.edtApp.entite.Enum.EStatutCours;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoursDto {

    private String id;

    @NotNull
    private LocalDate date;

    @NotNull(message = " Veuillez choisir un crénaux")
    private ECrenau crenau;

    private EStatutCours statutCours;

    private EDisponibiliteProf disponibiliteProf;

    @NotBlank(message = "Identifiant de la salle obligatoire !")
    private String idSalle;

    @NotBlank(message = "Identifiant du module obligatoire !")
    private String idMatiere;

    @NotBlank(message = "Identifiant de l'emploi du temps obligatoire !")
    private String idEmploiDuTemps;

}
