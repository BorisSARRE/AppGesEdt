package com.EdtAppProject.edtApp.dto;

import com.EdtAppProject.edtApp.entite.Enum.ENiveau;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FiliereDto {

    private String id;

    @NotBlank(message = "Nom de la filière obligatoire !")
    private String nomFiliere;

    private String description;

    @NotNull(message = "Niveau de la filère obligatoire !")
    private ENiveau niveau;

}
