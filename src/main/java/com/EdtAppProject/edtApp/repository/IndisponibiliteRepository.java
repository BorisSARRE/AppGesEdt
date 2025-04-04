package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Indisponibilite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndisponibiliteRepository extends JpaRepository<Indisponibilite, String> {
}
