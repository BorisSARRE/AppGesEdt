package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Devoir;
import com.EdtAppProject.edtApp.entite.Enum.ECrenau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DevoirRepository extends JpaRepository<Devoir, String> {

    boolean existsByDateAndCrenau(LocalDate date, ECrenau crenau);
}
