package com.EdtAppProject.edtApp.repository;

import com.EdtAppProject.edtApp.entite.Enum.ETitreEtudiant;
import com.EdtAppProject.edtApp.entite.Etudiant;
import com.EdtAppProject.edtApp.entite.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    Optional<Etudiant> findByFiliereAndTitreEtudiant(Filiere filiere, ETitreEtudiant t);
    List<Etudiant> findByTitreEtudiant(ETitreEtudiant titreEtudiant);
}
