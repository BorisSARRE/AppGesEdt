package com.EdtAppProject.edtApp.dto;

import com.EdtAppProject.edtApp.entite.Enum.EStatutCompte;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private String type; // Type d'utilisateur
    private String refreshToken;
    private String id;
    private String email;
    private String nom;
    private String prenom;
    private EStatutCompte statutCompte;
    private List<String> roles;
}
