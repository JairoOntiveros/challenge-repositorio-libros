package com.jairoontiveros.challenge_repositorio_libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Entity
@Table(name = "libros")
public class Libro {

    //asignando el atributo Id como el Id de la base de datos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String titulo;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "libro_autor",
    joinColumns = @JoinColumn(name = "libro_id"),
    inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores;
    private Integer descargas;

    @ElementCollection(targetClass = Idiomas.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Idiomas> idiomas;

    public Libro(){

    }

    public Libro(DatosLibros libroOtenido) {
        this.titulo = libroOtenido.titulo();
        this.autores = libroOtenido.autores().stream()
                .map(a->new Autor(a.nombre(),a.fechaNacimiento(),a.fechaDeMuerte()))
                .toList();
        this.descargas = libroOtenido.descargas();
        this.idiomas = libroOtenido.idiomas().stream()
                .map(idioma-> {
                    try {
                        return Optional.ofNullable(idioma)
                                .map(Idiomas::fromString)
                                .orElse(null);
                    } catch (IllegalArgumentException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public List<Idiomas> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idiomas> idiomas) {
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


