package com.EdtAppProject.edtApp.entite;

import com.EdtAppProject.edtApp.entite.Enum.ETypeParent;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@DiscriminatorValue("PARENT")
public class Parent extends Utilisateur {

    @Column(name = "typeParent", nullable = false)
    private ETypeParent typeParent;

    @Column(name = "lieuResidence")
    private String lieuResidence;
}
