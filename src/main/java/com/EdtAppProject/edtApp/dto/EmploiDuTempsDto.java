package com.EdtAppProject.edtApp.dto;

import com.EdtAppProject.edtApp.entite.Enum.EStatutEdt;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmploiDuTempsDto {

    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime datePublication;

    @NotNull(message = "La date de début est obligatoire !")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFin;

    private EStatutEdt statutEdt;

    @NotBlank(message = "Identifiant de la filière obligatoire !")
    private String idFiliere;

}
