package com.EdtAppProject.edtApp.entite;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@DiscriminatorValue("ENSEIGNANT")
public class Enseignant extends Utilisateur{

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "grade")
    private String grade;

    @Column(name = "specialite")
    private String specialite;

    @ManyToMany(mappedBy = "enseignants")
    private List<Matiere> matiereses = new ArrayList<>();
}
