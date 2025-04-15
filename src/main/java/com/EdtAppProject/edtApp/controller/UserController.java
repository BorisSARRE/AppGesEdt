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
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest request,
                                                                 HttpServletRequest httpServletRequest) {
        return new  ResponseEntity<>(utilisateurService.changePassword(request, httpServletRequest),HttpStatus.OK);
    }
}
