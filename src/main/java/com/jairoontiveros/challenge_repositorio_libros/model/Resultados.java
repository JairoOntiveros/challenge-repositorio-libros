package com.jairoontiveros.challenge_repositorio_libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Resultados(@JsonAlias("results") List<DatosLibros> resultados) {
}
