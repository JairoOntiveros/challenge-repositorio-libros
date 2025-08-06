package com.jairoontiveros.challenge_repositorio_libros.model;

public enum Idiomas {
    EN("en", "Inglés"),
    ES("es", "Español"),
    FR("fr", "Francés"),
    FI("fi", "Finlandés"),
    DE("de", "Alemán"),
    IT("it", "Italiano"),
    PT("pt", "Portugués"),
    ZH("zh", "Chino"),
    JA("ja", "Japonés"),
    RU("ru", "Ruso"),
    OTRO("otro","Otro");


    private String languages;
    private String idiomaCompleto;

    Idiomas(String langueajes, String idiomaCompleto) {
        this.languages = langueajes;
        this.idiomaCompleto = idiomaCompleto;
    }

    public String getIdiomaCompleto() {
        return idiomaCompleto;
    }

    public String getLanguages() {
        return languages;
    }

    public static Idiomas fromString(String text) {
        for (Idiomas idioma : Idiomas.values()) {
            if (idioma.languages.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        return OTRO;
    }

    public static Idiomas fromNombreCompleto(String text) {
        for (Idiomas idioma : Idiomas.values()) {
            if (idioma.idiomaCompleto.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        return OTRO;
    }

}


