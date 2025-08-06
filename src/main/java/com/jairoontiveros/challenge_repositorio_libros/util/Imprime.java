package com.jairoontiveros.challenge_repositorio_libros.util;

import com.jairoontiveros.challenge_repositorio_libros.model.Autor;
import com.jairoontiveros.challenge_repositorio_libros.model.Idiomas;
import com.jairoontiveros.challenge_repositorio_libros.model.Libro;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//Esta clase es usada para imprimir libros y autores de manera manual
// ya que hinernate no permite acceder a la vez a Libro y Autor

public class Imprime {
    public static void imprimirLibros(Iterable<Libro> libros) {
        for (Libro libro : libros) {
            System.out.println("════════════════════════════════════════════");
            System.out.println("Título      : " + libro.getTitulo());

            System.out.println("\nAutores     :");
            for (Autor autor : libro.getAutores()) {
                String fechaNacimiento = (autor.getFechaNacimiento() != null)
                        ? autor.getFechaNacimiento().toString()
                        : "Desconocida";
                String fechaDeMuerte = (autor.getFechaDeMuerte() != null)
                        ? autor.getFechaDeMuerte().toString()
                        : "Desconocida";

                System.out.println("  - " + autor.getNombre());
                System.out.println("    Nacimiento     : " + fechaNacimiento);
                System.out.println("    Fallecimiento  : " + fechaDeMuerte);
            }

            System.out.println("\nDescargas   : " + libro.getDescargas());

            String idiomasTexto = libro.getIdiomas().stream()
                    .map(Idiomas::getIdiomaCompleto)
                    .collect(Collectors.joining(", "));
            System.out.println("\nIdiomas     : " + idiomasTexto);
            System.out.println("════════════════════════════════════════════\n");
        }
    }

    public static void imprimirLibro(Libro libro) {
        if (libro == null) {
            System.out.println("No se encontró ningún libro para mostrar.");
        } else {
            imprimirLibros(Collections.singletonList(libro));
        }
    }

    public static void imprimirAutores(List<Autor> autores) {
        for (Autor autor : autores) {
            System.out.println("════════════════════════════════════════════");
            System.out.println("Autor       : " + autor.getNombre());

            String fechaNacimiento = (autor.getFechaNacimiento() != null)
                    ? autor.getFechaNacimiento().toString()
                    : "Desconocida";
            String fechaDeMuerte = (autor.getFechaDeMuerte() != null)
                    ? autor.getFechaDeMuerte().toString()
                    : "Desconocida";

            System.out.println("Nacimiento  : " + fechaNacimiento);
            System.out.println("Fallecimiento: " + fechaDeMuerte);

            System.out.println("\nLibros      :");
            if (autor.getLibros() == null || autor.getLibros().isEmpty()) {
                System.out.println("  (No se encontraron libros asociados)");
            } else {
                for (Libro libro : autor.getLibros()) {
                    System.out.println("  - " + libro.getTitulo());
                }
            }

            System.out.println("════════════════════════════════════════════\n");
        }
    }
}
