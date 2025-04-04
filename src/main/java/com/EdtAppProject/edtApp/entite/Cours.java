package com.EdtAppProject.edtApp.entite;

import com.EdtAppProject.edtApp.entite.Enum.ECrenaux;
import com.EdtAppProject.edtApp.entite.Enum.EDisponibiliteProf;
import com.EdtAppProject.edtApp.entite.Enum.EStatutCours;
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
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "crenaux", nullable = false)
    private ECrenaux crenaux;

    @Column(name = "statutCours")
    private EStatutCours statutCours;

    @Column(name = "disponibiliteProf")
    private EDisponibiliteProf disponibiliteProf;

    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Matiere module;


}
