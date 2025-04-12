package com.EdtAppProject.edtApp.service;

import com.EdtAppProject.edtApp.dto.ProfileUpdateRequest;
import com.EdtAppProject.edtApp.entite.Enum.EStatutCompte;
import com.EdtAppProject.edtApp.entite.Utilisateur;
import com.EdtAppProject.edtApp.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public Utilisateur getUtilisateurCourant() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    public Utilisateur activerCompte(String id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        utilisateur.setStatutCompte(EStatutCompte.ACTIF);
        return utilisateurRepository.save(utilisateur);
    }

    public void bloquerCompte(String id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utilisateur non trouvé"));

        utilisateur.setStatutCompte(EStatutCompte.BLOQUE);
        utilisateurRepository.save(utilisateur);
    }

    public Utilisateur updateProfile(String id, ProfileUpdateRequest request) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setTelephone(request.getTelephone());

        // Mise à jour du mot de passe si fourni
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            utilisateur.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        // Ajouter d'autres champs spécifiques selon le type d'utilisateur

        return utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    public List<Utilisateur> getUsersByStatut(EStatutCompte statutCompte) {
        return utilisateurRepository.findByStatutCompte(statutCompte);
    }

}
