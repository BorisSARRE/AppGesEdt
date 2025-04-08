package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, String> {

    boolean existsByIntituleAndFiliereId(String intitule, String idFiliere);
    List<Matiere> findByFiliereId(String idFiliere);
}
