package com.EdtAppProject.edtApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordResponse {

    private String message;
    private String emailUtilisateur;
    private boolean success;
}
