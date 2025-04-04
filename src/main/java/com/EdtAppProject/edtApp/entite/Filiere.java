package com.EdtAppProject.edtApp.entite;

import com.EdtAppProject.edtApp.entite.Enum.ENiveau;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Filiere {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nomFiliere", nullable = false)
    private String nomFiliere;

    @Column(name = "description")
    private String description;

    @Column(name = "niveau", nullable = false)
    private ENiveau niveau;
}
