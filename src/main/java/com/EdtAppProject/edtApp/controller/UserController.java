package com.EdtAppProject.edtApp.controller;

import com.EdtAppProject.edtApp.dto.ChangePasswordRequest;
import com.EdtAppProject.edtApp.dto.ChangePasswordResponse;
import com.EdtAppProject.edtApp.dto.ProfileUpdateRequest;
import com.EdtAppProject.edtApp.entite.Utilisateur;
import com.EdtAppProject.edtApp.service.UtilisateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UtilisateurService utilisateurService;

    @GetMapping("/me")
    public ResponseEntity<Utilisateur> getCurrentUser() {
        return ResponseEntity.ok(utilisateurService.getUtilisateurCourant());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateProfile(
            @PathVariable String id,
            @Valid @RequestBody ProfileUpdateRequest request
    ) {
        return ResponseEntity.ok(utilisateurService.updateProfile(id, request));
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        return new  ResponseEntity<>(utilisateurService.changePassword(request),HttpStatus.OK);
    }
}
