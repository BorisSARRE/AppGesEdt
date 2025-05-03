package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Enum.EStatutCompte;
import com.EdtAppProject.edtApp.entite.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
    Optional<Utilisateur> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Utilisateur> findByStatutCompte(EStatutCompte statutCompte);

    @Query("SELECT u.filiere.id FROM Utilisateur u WHERE u.id = :userId")
    String findFiliereIdByUserId(@Param("userId") String userId);
}
