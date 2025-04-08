package com.EdtAppProject.edtApp.entite.Enum;

public enum ECrenau {
    MATIN("8h-12h"),
    SOIR("14h-18h");

    private final String plageHoraire;

    ECrenau(String plageHoraire) {
        this.plageHoraire = plageHoraire;
    }

    public String getPlageHoraire() {
        return plageHoraire;
    }
}
