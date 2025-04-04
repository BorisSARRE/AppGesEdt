package com.EdtAppProject.edtApp.dto;

import com.EdtAppProject.edtApp.entite.Enum.ECrenaux;
import com.EdtAppProject.edtApp.entite.Enum.EDisponibiliteProf;
import com.EdtAppProject.edtApp.entite.Enum.EStatutCours;
import jakarta.validation.constraints.NotBlank;
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

    @NonNull
    private LocalDate date;

    @NotBlank(message = " Veuillez choisir un crénaux")
    private ECrenaux crenaux;

    private EStatutCours statutCours;

    private EDisponibiliteProf disponibiliteProf;

    @NotBlank(message = "Identifiant de la salle obligatoire !")
    private String idSalle;

    @NotBlank(message = "Identifiant du module obligatoire !")
    private String idMatiere;

}
