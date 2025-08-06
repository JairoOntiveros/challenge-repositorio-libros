package com.jairoontiveros.challenge_repositorio_libros.principal;


import com.jairoontiveros.challenge_repositorio_libros.model.*;
import com.jairoontiveros.challenge_repositorio_libros.repositorio.RepositorioAutor;
import com.jairoontiveros.challenge_repositorio_libros.repositorio.RepositorioLibro;
import com.jairoontiveros.challenge_repositorio_libros.service.APIService;
import com.jairoontiveros.challenge_repositorio_libros.service.ConvierteDatos;
import com.jairoontiveros.challenge_repositorio_libros.util.Imprime;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.jairoontiveros.challenge_repositorio_libros.util.Imprime.imprimirAutores;
import static com.jairoontiveros.challenge_repositorio_libros.util.Imprime.imprimirLibros;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/";
    private APIService apiService = new APIService();
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<Libro> librosGuardados = new ArrayList<>();
    private List<Autor> autoresGuardados = new ArrayList<>();
    private RepositorioLibro repositorio;
    private RepositorioAutor repositorioAutor;

    public Principal(RepositorioLibro repository, RepositorioAutor repositorioAutor) {
        this.repositorio = repository;
        this.repositorioAutor = repositorioAutor;
    }


    public void muestraMenu(){

        var opcion = -1;
        while(opcion != 0){
            var menu = """
                    1 - Buscar libro en la Web
                    2 - Mostar libros registrados
                    3 - Mostrar autores registrados
                    4 - Consultar autores vivos por año
                    5 - Mostrar libros por idioma
                    
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            String entrada = teclado.nextLine();
            //validacion de entrada del menú
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida");
                continue;
            }

            switch (opcion){
                case 1:
                   var libro = buscarLibroWeb();
                    System.out.println(libro);
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    consultarAutoresVivosPorAnio();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicacion.......");
                    break;
                default:
                    System.out.println("Opcion Inválida");

            }
        }
    }

    private DatosLibros getLibroWeb(){
        System.out.println("Escribe el libro que quieres buscar y guardar");
        var nombreLibro = teclado.nextLine();
        var json = apiService.obtenerDatos(URL_BASE+"?search="+nombreLibro.replace(" ","%20"));
       Resultados respuestaLibro = conversor.mapeaDatos(json, Resultados.class);

        //revisando si existe el libro en la respuesta
        if (respuestaLibro.resultados().isEmpty()){
            System.out.println("No se encontraron resultados en la web");
            return null;
        }
        //pasando los datos parseados al record adecuado
        DatosLibros libroObtenido = respuestaLibro.resultados().get(0);

       return libroObtenido;
    }


    public Libro buscarLibroWeb(){
        DatosLibros libroOptenido = getLibroWeb();
        //Si getLibroWeb retornó null
        if (libroOptenido == null){
            return null;
        }

        Libro libro = new Libro(libroOptenido);
        System.out.println("Libro guardado exitosamente");
        repositorio.save(libro);
        return libro;

    }


    public void mostrarLibrosRegistrados() {
        librosGuardados = repositorio.findAll();
        Imprime.imprimirLibros(librosGuardados);
    }


    public void mostrarAutoresRegistrados(){
        autoresGuardados = repositorioAutor.findAllWithLibros();

        if (autoresGuardados.isEmpty()){
            System.out.println("NO hay autores registrados");
            return;
        }

        imprimirAutores(autoresGuardados);


    }

    private void consultarAutoresVivosPorAnio() {
        System.out.print("Ingresa el año para buscar autores vivos: ");
        int anho = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autores = repositorioAutor.buscarAutoresVivosPorAnho(anho);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anho);
        } else {
            System.out.println("Autores vivos en el año " + anho + ":");
            imprimirAutores(autores);
        }
    }


    private void mostrarLibrosPorIdioma(){
        System.out.print("Ingrese el idioma (código, ej: es, en): ");
        String idioma = teclado.nextLine().trim();

        Idiomas idiomaSeleccionado = Idiomas.fromString(idioma);
        List<Libro> librosPorIdioma = repositorio.findByIdioma(idiomaSeleccionado);

        if(librosPorIdioma.isEmpty()){
            System.out.println("No se encontraron libros para el idioma: "+idiomaSeleccionado.getLanguages());
        }else{
            System.out.println("Libros encontrados para el idioma "+idiomaSeleccionado.getLanguages()+":");
            for (Libro libro: librosPorIdioma){
                imprimirLibros(librosPorIdioma);
            }
        }
    }

}
