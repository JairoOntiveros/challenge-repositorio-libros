package com.jairoontiveros.challenge_repositorio_libros.util;

import com.jairoontiveros.challenge_repositorio_libros.model.Autor;
import com.jairoontiveros.challenge_repositorio_libros.model.Idiomas;
import com.jairoontiveros.challenge_repositorio_libros.model.Libro;

import java.util.List;
import java.util.stream.Collectors;

//Esta clase es usada para imprimir libros y autores de manera manual
// ya que hinernate no permite acceder a la vez a Libro y Autor

public class Imprime {
    public static void imprimirLibros(Iterable<Libro> libros) {
        for (Libro libro : libros) {

            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autores:");
            for (Autor autor : libro.getAutores()) {
                String fechaNacimiento = (autor.getFechaNacimiento() != null)
                        ? autor.getFechaNacimiento().toString()
                        : "Desconocida";
                String fechaDeMuerte = (autor.getFechaDeMuerte() != null)
                        ? autor.getFechaDeMuerte().toString()
                        : "Desconocida";

                System.out.println(" - " + autor.getNombre() +
                        " (nacido: " + fechaNacimiento +
                        ", fallecido: " + fechaDeMuerte + ")");
            }
            System.out.println("Descargas: " + libro.getDescargas());

            String idiomasTexto = libro.getIdiomas().stream()
                    .map(Idiomas::getIdiomaCompleto)
                    .collect(Collectors.joining(", "));
            System.out.println("Idiomas: " + idiomasTexto);
            System.out.println("----------------------------------");
        }
    }

    public static void imprimirAutores(List<Autor> autores) {
        for (Autor autor : autores) {
            //comprobacion de Null

            String fechaNacimiento = (autor.getFechaNacimiento() != null)
                    ? autor.getFechaNacimiento().toString()
                    : "Desconocida";
            String fechaDeMuerte = (autor.getFechaDeMuerte() != null)
                    ? autor.getFechaDeMuerte().toString()
                    : "Desconocida";





            System.out.println("Autor: " + autor.getNombre() +
                    " (nacido: " + fechaNacimiento +
                    ", fallecido: " + fechaDeMuerte + ")");
            System.out.println("Libros:");
            for (Libro libro : autor.getLibros()) {
                System.out.println(" - " + libro.getTitulo());
            }
            System.out.println("----------------------------------");
        }
    }
}
