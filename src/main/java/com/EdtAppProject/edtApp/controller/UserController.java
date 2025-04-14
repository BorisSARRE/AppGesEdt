package com.EdtAppProject.edtApp.controller;

import com.EdtAppProject.edtApp.dto.ChangePasswordRequest;
import com.EdtAppProject.edtApp.dto.ChangePasswordResponse;
import com.EdtAppProject.edtApp.dto.ProfileUpdateRequest;
import com.EdtAppProject.edtApp.entite.Utilisateur;
import com.EdtAppProject.edtApp.service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UtilisateurService utilisateurService;

    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT', 'ETUDIANT', 'PARENT')")
    @GetMapping("/me")
    public ResponseEntity<Utilisateur> getCurrentUser() {
        return ResponseEntity.ok(utilisateurService.getUtilisateurCourant());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT', 'ETUDIANT', 'PARENT')")
    public ResponseEntity<Utilisateur> updateProfile(
            @PathVariable String id,
            @Valid @RequestBody ProfileUpdateRequest request
    ) {
        return ResponseEntity.ok(utilisateurService.updateProfile(id, request));
    }

    @PostMapping("/changePassword")
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT', 'ETUDIANT', 'PARENT')")
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        return new  ResponseEntity<>(utilisateurService.changePassword(request),HttpStatus.OK);
    }

//    @PostMapping("/change-password")
//    public ResponseEntity<ChangePasswordResponse> changePassword(
//            HttpServletRequest request,
//            @RequestBody ChangePasswordRequest changePasswordRequest) {
//
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token d'authentification requis");
//        }
//
//        String token = authHeader.substring(7);
//
//        try {
//            ChangePasswordResponse response = utilisateurService.changePassword(token, changePasswordRequest);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    e.getMessage() != null ? e.getMessage() : "Une erreur est survenue lors du changement de mot de passe"
//            );
//        }
//    }
}
