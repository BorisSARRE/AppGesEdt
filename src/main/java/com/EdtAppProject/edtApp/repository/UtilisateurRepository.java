package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
}
