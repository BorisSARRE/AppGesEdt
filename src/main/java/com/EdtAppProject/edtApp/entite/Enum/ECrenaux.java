package com.EdtAppProject.edtApp.entite.Enum;

public enum ECrenaux {
    H8_12("8h-12h"),
    H14_18("14h-18h");

    private final String label;

    ECrenaux(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
