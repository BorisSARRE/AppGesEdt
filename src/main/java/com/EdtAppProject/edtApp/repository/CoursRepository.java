package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Cours;
import com.EdtAppProject.edtApp.entite.Enum.ECrenau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CoursRepository extends JpaRepository<Cours,String> {

    boolean existsByDateAndCrenauAndMatiereId(LocalDate date, ECrenau crenau, String idMatiere);
    boolean existsByDateAndCrenauAndSalleId(LocalDate date, ECrenau crenau, String idSalle);
    List<Cours> findByEmploiDuTempsIdAndMatiereFiliereId(String idEmploiDuTemps, String idFiliere);
}
