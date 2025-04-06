package com.EdtAppProject.edtApp.entite;

import com.EdtAppProject.edtApp.entite.Enum.EDisponibiliteSalle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "numeroSalle", nullable = false)
    private String numeroSalle;

    @Enumerated(EnumType.STRING)
    @Column(name = "disponibiliteSalle")
    private EDisponibiliteSalle disponibiliteSalle;

//    @Version
//    private Long version;
}
