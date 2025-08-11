package com.nekorp.portafolio.proyectos.entity;

public enum ProjectType {
    WEB("Aplicación Web"),
    DESKTOP("Aplicación Desktop"),
    MOBILE("Aplicación Mobile"),
    API("API/Backend"),
    LIBRARY("Librería/Framework"),
    OTHER("Otros");

    private final String displayName;

    ProjectType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
