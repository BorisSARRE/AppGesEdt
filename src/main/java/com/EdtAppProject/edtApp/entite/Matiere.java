package com.EdtAppProject.edtApp.entite;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "intitule", nullable = false)
    private String intitule;

    @Column(name = "volumeHoraire" )
    private int volumeHoraire;

    @ManyToMany
    @JoinTable(name = "matiere_enseignant",
            joinColumns = @JoinColumn(name = "matiere_id"),
            inverseJoinColumns = @JoinColumn(name = "enseignant_id"))

    private List<Enseignant> enseignants = new ArrayList<>();

}
