package com.EdtAppProject.edtApp.service;

import com.EdtAppProject.edtApp.dto.AuthResponse;
import com.EdtAppProject.edtApp.dto.LoginRequest;
import com.EdtAppProject.edtApp.dto.RegisterRequest;
import com.EdtAppProject.edtApp.entite.Administration;
import com.EdtAppProject.edtApp.entite.Enseignant;
import com.EdtAppProject.edtApp.entite.Enum.EStatutCompte;
import com.EdtAppProject.edtApp.entite.Etudiant;
import com.EdtAppProject.edtApp.entite.Filiere;
import com.EdtAppProject.edtApp.entite.Parent;
import com.EdtAppProject.edtApp.entite.Utilisateur;
import com.EdtAppProject.edtApp.repository.AdministrationRepository;
import com.EdtAppProject.edtApp.repository.EnseignantRepository;
import com.EdtAppProject.edtApp.repository.EtudiantRepository;
import com.EdtAppProject.edtApp.repository.FiliereRepository;
import com.EdtAppProject.edtApp.repository.ParentRepository;
import com.EdtAppProject.edtApp.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final ParentRepository parentRepository;
    private final EtudiantRepository etudiantRepository;
    private final EnseignantRepository enseignantRepository;
    private final AdministrationRepository administrationRepository;
    private final FiliereRepository filiereRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        // Vérifier si l'email existe déjà
        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email déjà utilisé");
        }

        // Création de l'utilisateur selon son rôle
        Utilisateur utilisateur;

        switch (request.getRole()) {
            case ADMIN:
                Administration admin = new Administration();
                admin.setNom(request.getNom());
                admin.setPrenom(request.getPrenom());
                admin.setSexe(request.getSexe());
                admin.setTelephone(request.getTelephone());
                admin.setEmail(request.getEmail());
                admin.setPassword(passwordEncoder.encode(request.getPassword()));
                admin.setRole(request.getRole());
                admin.setMatricule(request.getMatricule());
                admin.setTypeAdmin(request.getTypeAdmin());
                admin.setStatutCompte(EStatutCompte.ACTIF);
                utilisateurRepository.save(admin);
                return buildAuthResponse(admin);

            case ENSEIGNANT:

                Enseignant enseignant = new Enseignant();
                enseignant.setNom(request.getNom());
                enseignant.setPrenom(request.getPrenom());
                enseignant.setSexe(request.getSexe());
                enseignant.setTelephone(request.getTelephone());
                enseignant.setEmail(request.getEmail());
                enseignant.setPassword(passwordEncoder.encode(request.getPassword()));
                enseignant.setRole(request.getRole());
                enseignant.setMatricule(request.getMatricule());
                enseignant.setTypeEnseignant(request.getTypeEnseignant());
                enseignant.setGrade(request.getGrade());
                enseignant.setSpecialite(request.getSpecialite());
                enseignant.setStatutCompte(EStatutCompte.EN_ATTENTE);
                utilisateurRepository.save(enseignant);
                return buildAuthResponse(enseignant);


            case ETUDIANT:
                Etudiant etudiant = new Etudiant();
                etudiant.setNom(request.getNom());
                etudiant.setPrenom(request.getPrenom());
                etudiant.setSexe(request.getSexe());
                etudiant.setTelephone(request.getTelephone());
                etudiant.setEmail(request.getEmail());
                etudiant.setPassword(passwordEncoder.encode(request.getPassword()));
                etudiant.setRole(request.getRole());
                etudiant.setIne(request.getIne());
                etudiant.setTitreEtudiant(request.getTitreEtudiant());
                etudiant.setStatutCompte(EStatutCompte.EN_ATTENTE);
                utilisateurRepository.save(etudiant);


            if (request.getParentId() != null) {
                Parent parent = parentRepository.findById(request.getParentId())
                        .orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Parent non trouvé"));
                etudiant.setParent(parent);
            }

            if (request.getFiliereId() != null) {
                Filiere filiere = filiereRepository.findById(request.getFiliereId())
                        .orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND,"Filière non trouvée"));
                etudiant.setFiliere(filiere);
            }
                return buildAuthResponse(etudiant);


            case PARENT:
                Parent parent = new Parent();
                parent.setNom(request.getNom());
                parent.setPrenom(request.getPrenom());
                parent.setSexe(request.getSexe());
                parent.setTelephone(request.getTelephone());
                parent.setEmail(request.getEmail());
                parent.setPassword(passwordEncoder.encode(request.getPassword()));
                parent.setRole(request.getRole());
                parent.setTypeParent(request.getTypeParent());
                parent.setLieuResidence(request.getLieuResidence());
                parent.setStatutCompte(EStatutCompte.EN_ATTENTE);
                return buildAuthResponse(parent);

            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rôle invalide");
        }


    }

    public AuthResponse authenticate(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"Email ou mot de passe incorrect");
        }

        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Utilisateur non trouvé"));

        if (utilisateur.getStatutCompte() == EStatutCompte.EN_ATTENTE) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre compte est en attente d'activation par l'administration");
        }

        if (utilisateur.getStatutCompte() == EStatutCompte.BLOQUE) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Votre compte a été bloqué");
        }

        return buildAuthResponse(utilisateur);
    }

    private AuthResponse buildAuthResponse(Utilisateur utilisateur) {
        String jwtToken = jwtService.generateToken(utilisateur);
        String refreshToken = jwtService.generateRefreshToken(utilisateur);

        return AuthResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .id(utilisateur.getId())
                .email(utilisateur.getEmail())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .statutCompte(utilisateur.getStatutCompte())
                .type(utilisateur.getClass().getSimpleName())
                .roles(List.of(utilisateur.getRole().name()))
                .build();
    }

    public AuthResponse refreshToken(String refreshToken) {
        String email = jwtService.extractUsername(refreshToken);
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        if (jwtService.isTokenValid(refreshToken, utilisateur)) {
            return buildAuthResponse(utilisateur);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token de rafraîchissement invalide");
        }
    }

}
