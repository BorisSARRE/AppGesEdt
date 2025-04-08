package com.EdtAppProject.edtApp.dto;

import com.EdtAppProject.edtApp.entite.Enum.ETypeParent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParentDto extends UtilisateurDto {

    //@NotBlank(message = "Type du parent obligatoire !")
    private ETypeParent typeParent;

    private String lieuResidence;


}
