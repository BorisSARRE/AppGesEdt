package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Enum.ENiveau;
import com.EdtAppProject.edtApp.entite.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, String> {

    boolean existsByNomFiliereAndNiveau(String nomFiliere, ENiveau niveau);
    boolean existsByNomFiliereAndNiveauAndDescription(String nomFiliere, ENiveau niveau, String description);

}
