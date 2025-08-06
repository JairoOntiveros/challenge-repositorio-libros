package com.jairoontiveros.challenge_repositorio_libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutores(@JsonAlias("name") String nombre,
                           @JsonAlias("birth_year") Integer fechaNacimiento,
                           @JsonAlias("death_year") Integer fechaDeMuerte) {
}
