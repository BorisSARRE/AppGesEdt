package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Devoir;
import com.EdtAppProject.edtApp.entite.Enum.ECrenau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DevoirRepository extends JpaRepository<Devoir, String> {


    boolean existsByDateAndCrenau(LocalDate date, ECrenau crenau);
    boolean existsByDateAndCrenauAndDureeAndMatiereId(LocalDate date, ECrenau crenau, float durre, String idMatiere);
    boolean existsByDateAndCrenauAndSalleId(LocalDate date, ECrenau crenau, String idSalle);

    @Query("SELECT d FROM Devoir d WHERE d.emploiDuTemps.id = :idEdt")
    List<Devoir> findByEmploiDuTempsIdAndMatiereFiliereId(@Param("idEdt") String idEmploiDuTemps, String idFiliere);
}
