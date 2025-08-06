package com.jairoontiveros.challenge_repositorio_libros.repositorio;

import com.jairoontiveros.challenge_repositorio_libros.model.Autor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioAutor extends JpaRepository<Autor,Long> {

@Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllWithLibros();

    @EntityGraph(attributePaths = {"libros"})
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :anho AND (a.fechaDeMuerte IS NULL OR a.fechaDeMuerte > :anho)")
    List<Autor> buscarAutoresVivosPorAnho(Integer anho);
}
