package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepository extends JpaRepository<Cours,String> {
}
