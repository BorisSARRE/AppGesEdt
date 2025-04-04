package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, String> {
}
