package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Indisponibilite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IndisponibiliteRepository extends JpaRepository<Indisponibilite, String> {
    List<Indisponibilite> findByEnseignantId(String enseignantId);
    boolean existsByDateDebutAndDateFinAndEnseignantId(LocalDate dateDbut, LocalDate dateFin, String idEnseignant);
}
