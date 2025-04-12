package com.EdtAppProject.edtApp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateRequest {

    private String nom;
    private String prenom;
    private String telephone;
    private String password;

}
