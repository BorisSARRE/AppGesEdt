package com.EdtAppProject.edtApp.dto;

import com.EdtAppProject.edtApp.entite.Enum.EDisponibiliteSalle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalleDto {

    private String id;

    @NotBlank(message = "Numéro de salle obligatoire !")
    @Pattern(regexp = "^[A-D]\\d{1,2}$", message = "Le numéro de salle doit commencer par A, B, C ou D suivi de deux chiffres")
    private String numeroSalle;

    private EDisponibiliteSalle disponibiliteSalle;
}
