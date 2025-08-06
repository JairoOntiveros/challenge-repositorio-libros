package com.jairoontiveros.challenge_repositorio_libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class Libro {


   private String titulo;
   private List<DatosAutores> autores;
   private Integer descargas;
   private List<String> idiomas;


    public Libro(DatosLibros libroOtenido) {
        this.titulo = libroOtenido.titulo();
        this.autores = libroOtenido.autores();
        this.descargas = libroOtenido.descargas();
        this.idiomas = libroOtenido.idiomas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<DatosAutores> getAutores() {
        return autores;
    }

    public void setAutores(List<DatosAutores> autores) {
        this.autores = autores;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", descargas=" + descargas +
                ", idiomas=" + idiomas;
    }
}


