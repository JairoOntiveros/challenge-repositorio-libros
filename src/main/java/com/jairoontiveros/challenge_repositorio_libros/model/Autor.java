package com.jairoontiveros.challenge_repositorio_libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaDeMuerte;

    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;

    public Autor(){

    }

    public Autor(String nombre,Integer fechaNacimiento,Integer fechaDeMuerte  ) {
        this.fechaDeMuerte = fechaDeMuerte;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(Integer fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + (fechaNacimiento != null ? fechaNacimiento: "Desconocida") +
                ", fechaDeMuerte=" + (fechaDeMuerte != null ? fechaDeMuerte: "Desconocida");
    }
}

