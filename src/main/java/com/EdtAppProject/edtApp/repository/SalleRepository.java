package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleRepository extends JpaRepository<Salle, String> {

    boolean existsByIdOrNumeroSalle(String id, String numeroSalle);
    boolean existsByNumeroSalleAndIdNot(String numeroSalle, String idSalle );
}
