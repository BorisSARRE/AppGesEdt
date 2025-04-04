package com.EdtAppProject.edtApp.entite;

import com.EdtAppProject.edtApp.entite.Enum.ETitreEtudiant;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@DiscriminatorValue("ETUDIANT")
public class Etudiant extends Utilisateur {

    @Column(name = "ine", nullable = false)
    private String ine;

    @Column(name = "titreEtudiant", nullable = false)
    private ETitreEtudiant titreEtudiant;

    @ManyToOne
    @JoinColumn(name = "parent")
    private Parent parent;


    @ManyToOne
    @JoinColumn(name = "filiere")
    private Filiere filiere;
}
