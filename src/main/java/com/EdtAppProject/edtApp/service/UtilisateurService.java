package com.EdtAppProject.edtApp.service;

import com.EdtAppProject.edtApp.dto.ChangePasswordRequest;
import com.EdtAppProject.edtApp.dto.ChangePasswordResponse;
import com.EdtAppProject.edtApp.dto.LogoutRequest;
import com.EdtAppProject.edtApp.dto.LogoutResponse;
import com.EdtAppProject.edtApp.dto.ProfileUpdateRequest;
import com.EdtAppProject.edtApp.entite.Enum.EStatutCompte;
import com.EdtAppProject.edtApp.entite.Utilisateur;
import com.EdtAppProject.edtApp.repository.UtilisateurRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenBlacklistService tokenBlacklistService;

    public Utilisateur getUtilisateurCourant() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
    }

    public Utilisateur activerCompte(String id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setTelephone(request.getTelephone());

        // Mise à jour du mot de passe si fourni
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            utilisateur.setPassword(passwordEncoder.encode(request.getPassword()));
        }


        return utilisateurRepository.save(utilisateur);
    }

    @Transactional
    public ChangePasswordResponse changePassword(ChangePasswordRequest request, HttpServletRequest httpRequest) {

        // Extraire le token du header Authorization
        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader == null || authHeader.isBlank()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Header Authorization manquant");
        }

        if (!authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Format du token invalide (doit commencer par 'Bearer ')");
        }

        String token = authHeader.substring(7);

        if (token.isBlank()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token vide");
        }

        // Vérifier si le token est présent
        if (token == null || token.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token d'authentification manquant");
        }


        // Extraire l'email du token pour identifier l'utilisateur
        try {
            String email = jwtService.extractUsername(token);

            // Récupérer l'utilisateur
            Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

            // Vérifier l'ancien mot de passe
            if (!passwordEncoder.matches(request.getOldPassword(), utilisateur.getPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ancien mot de passe incorrect");
            }

            // Vérifier que le nouveau mot de passe est différent de l'ancien
            if (passwordEncoder.matches(request.getNewPassword(), utilisateur.getPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Le nouveau mot de passe doit être différent de l'ancien");
            }

            // Changer le mot de passe
            utilisateur.setPassword(passwordEncoder.encode(request.getNewPassword()));
            utilisateurRepository.save(utilisateur);

            return ChangePasswordResponse.builder()
                    .message("Mot de passe modifié avec succès")
                    .emailUtilisateur(email)
                    .success(true)
                    .build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token invalide ou expiré");
        }
    }

    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    public List<Utilisateur> getUsersByStatut(EStatutCompte statutCompte) {
        return utilisateurRepository.findByStatutCompte(statutCompte);
    }

    @Transactional
    public LogoutResponse logout(HttpServletRequest request) {
        // Récupérer le token depuis le header Authorization
        String authHeader = request.getHeader("Authorization");
        String token = null;

        // Extraire le token du header (format typique: "Bearer token_value")
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        if (token == null || token.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token requis pour la déconnexion");
        }

        try {
            String email = jwtService.extractUsername(token);
            Date expirationDate = jwtService.getExpirationDate(token);

            // Ajouter le token à la liste noire jusqu'à sa date d'expiration
            tokenBlacklistService.blacklistToken(token, expirationDate);

            return LogoutResponse.builder()
                    .success(true)
                    .message("Déconnexion réussie")
                    .build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token invalide");
        }
    }


    @Transactional
    public ChangePasswordResponse changePassword(String token, ChangePasswordRequest request) {
        // Extraire l'email du token pour identifier l'utilisateur
        String email = jwtService.extractUsername(token);

        // Récupérer l'utilisateur
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        // Vérifier l'ancien mot de passe
        if (!passwordEncoder.matches(request.getOldPassword(), utilisateur.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ancien mot de passe incorrect");
        }

        // Vérifier que le nouveau mot de passe est différent de l'ancien
        if (passwordEncoder.matches(request.getNewPassword(), utilisateur.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Le nouveau mot de passe doit être différent de l'ancien");
        }

        // Changer le mot de passe
        utilisateur.setPassword(passwordEncoder.encode(request.getNewPassword()));
        utilisateurRepository.save(utilisateur);

        // Ajouter le token à la liste noire (optionnel pour forcer une reconnexion)
        Date expirationDate = jwtService.getExpirationDate(token);
        tokenBlacklistService.blacklistToken(token, expirationDate);

        return ChangePasswordResponse.builder()
                .message("Mot de passe modifié avec succès")
                .emailUtilisateur(email)
                .success(true)
                .build();
    }
}
