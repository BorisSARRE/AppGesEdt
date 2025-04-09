package com.EdtAppProject.edtApp.entite;

import com.EdtAppProject.edtApp.entite.Enum.ECrenau;
import com.EdtAppProject.edtApp.entite.Enum.ESatutDevoir;
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
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Devoir {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "crenau", nullable =false)
    private ECrenau crenau;

    @Column(name = "duree", nullable = false)
    private float duree;

    @Enumerated(EnumType.STRING)
    @Column(name = "statutDevoir")
    private ESatutDevoir statutDevoir;

    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "emploiDuTemps_id")
    private EmploiDuTemps emploiDuTemps;

}
