package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.EmploiDuTemps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploiDuTempsRepository extends JpaRepository<EmploiDuTemps, String> {
}
