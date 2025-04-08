package com.EdtAppProject.edtApp.entite;

import com.EdtAppProject.edtApp.entite.Enum.ETypeAdmin;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@DiscriminatorValue("ADMINISTRATION")
public class Administration extends Utilisateur {

    @Column(name = "matricule")
    private String matricule;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeAdmin", nullable = true)
    private ETypeAdmin typeAdmin;
}
