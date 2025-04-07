package com.EdtAppProject.edtApp.entite;

import com.EdtAppProject.edtApp.entite.Enum.ESemestre;
import com.EdtAppProject.edtApp.entite.Enum.EStatutMatiere;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    @Column(name = "volumeHoraire", nullable = false )
    private int volumeHoraire;

    @Column(name = "semestre", nullable = false)
    @Enumerated(EnumType.STRING)
    private ESemestre semestre;

    @Column(name = "statutMatiere", nullable = false)
    @Enumerated(EnumType.STRING)
    private EStatutMatiere statutMatiere;

    @ManyToOne
    @JoinColumn(name = "filiere")
    private Filiere filiere;

    @ManyToMany
    @JoinTable(name = "matiere_enseignant",
            joinColumns = @JoinColumn(name = "matiere_id"),
            inverseJoinColumns = @JoinColumn(name = "enseignant_id"))

    private List<Enseignant> enseignants = new ArrayList<>();

}
