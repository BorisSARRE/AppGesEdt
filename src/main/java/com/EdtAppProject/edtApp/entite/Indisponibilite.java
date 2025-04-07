package com.EdtAppProject.edtApp.entite;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Indisponibilite {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "dateDebut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "dateFin")
    private LocalDate dateFin;

    @Column(name = "motif")
    private String motif;

    @ManyToOne
    @JoinColumn(name = "enseignant")
    private Enseignant enseignant;
}
