package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, String> {
}
