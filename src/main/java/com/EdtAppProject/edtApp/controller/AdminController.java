package com.EdtAppProject.edtApp.controller;

import com.EdtAppProject.edtApp.entite.Enum.EStatutCompte;
import com.EdtAppProject.edtApp.entite.Utilisateur;
import com.EdtAppProject.edtApp.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UtilisateurService utilisateurService;

    @GetMapping("/users")
    public ResponseEntity<List<Utilisateur>> getAllUsers() {
        return ResponseEntity.ok(utilisateurService.getAllUsers());
    }

    @GetMapping("/users/pending")
    public ResponseEntity<List<Utilisateur>> getPendingUsers() {
        return ResponseEntity.ok(utilisateurService.getUsersByStatut(EStatutCompte.EN_ATTENTE));
    }

    @PutMapping("/users/{id}/activate")
    public ResponseEntity<Utilisateur> activateUser(@PathVariable String id) {
        Utilisateur utilisateur = utilisateurService.activerCompte(id);
        return new  ResponseEntity<>(utilisateur, HttpStatus.ACCEPTED);
    }

    @PutMapping("/users/{id}/block")
    public ResponseEntity<Void> blockUser(@PathVariable String id) {
        utilisateurService.bloquerCompte(id);
        return ResponseEntity.ok().build();
    }
}
