package com.jairoontiveros.challenge_repositorio_libros.repositorio;

import com.jairoontiveros.challenge_repositorio_libros.model.Idiomas;
import com.jairoontiveros.challenge_repositorio_libros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositorioLibro extends JpaRepository<Libro,Long> {

    @Query("SELECT l FROM Libro l JOIN l.idiomas i WHERE i= :idioma")
    List<Libro> findByIdioma(@Param("idioma") Idiomas idioma);

}
