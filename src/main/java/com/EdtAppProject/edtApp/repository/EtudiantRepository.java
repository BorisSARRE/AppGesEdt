package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
}
