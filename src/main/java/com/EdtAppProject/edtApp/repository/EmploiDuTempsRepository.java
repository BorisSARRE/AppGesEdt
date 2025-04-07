package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.EmploiDuTemps;
import com.EdtAppProject.edtApp.entite.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmploiDuTempsRepository extends JpaRepository<EmploiDuTemps, String> {

    boolean existsByDateDebutAndFiliere(LocalDate dateDebut, Filiere filiere);
    List<EmploiDuTemps> findByFiliereNomFiliereContainingIgnoreCase(String nomFiliere);
}
