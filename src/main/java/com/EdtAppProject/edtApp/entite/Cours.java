package com.EdtAppProject.edtApp.entite;

import com.EdtAppProject.edtApp.entite.Enum.ECrenaux;
import com.EdtAppProject.edtApp.entite.Enum.EDisponibiliteProf;
import com.EdtAppProject.edtApp.entite.Enum.EJourSemaine;
import com.EdtAppProject.edtApp.entite.Enum.EStatutCours;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "crenaux", nullable = false)
    private ECrenaux crenaux;

    @Enumerated(EnumType.STRING)
    @Column(name = "statutCours")
    private EStatutCours statutCours;

    @Enumerated(EnumType.STRING)
    @Column(name = "disponibiliteProf")
    private EDisponibiliteProf disponibiliteProf;


    @Enumerated(EnumType.STRING)
    @Column(name = "jourSemaine")
    private EJourSemaine jourSemaine;

    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Matiere matiere;


}
