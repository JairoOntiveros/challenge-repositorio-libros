package com.jairoontiveros.challenge_repositorio_libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(@JsonAlias("title") String titulo,
                          @JsonAlias("authors") List<DatosAutores> autores,
                          @JsonAlias("download_count") Integer descargas,
                          @JsonAlias("languages") List<String> idiomas,
                          @JsonAlias("count") Integer countea) {



}
