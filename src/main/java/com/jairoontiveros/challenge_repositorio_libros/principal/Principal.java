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

import static com.jairoontiveros.challenge_repositorio_libros.util.Imprime.*;

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
                    5 - Mostrar Estadisticas por idioma
                    6 - Mostrar libros por idioma
                    7 - Mostrar Top 10 libros descargados
                    
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            String entrada = teclado.nextLine();
            //validacion de entrada del menú
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, por favor seleccione un número");
                continue;
            }

            switch (opcion){
                case 1:
                   var libro = buscarLibroWeb();
                    imprimirLibro(libro);
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
                    mostrarEstadisticasPorIdioma();
                case 6:
                    mostrarLibrosPorIdioma();
                    break;
                case 7:
                    mostrarTop10LibrosDescargados();

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

        if(repositorio.existsByTitulo(libro.getTitulo())){
            System.out.println("Este libro ya existe en la base de datos.");
            return repositorio.findByTitulo(libro.getTitulo()).orElse(null);
        }


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

    public void mostrarEstadisticasPorIdioma(){
        System.out.print("Ingrese el idioma (código, ej: es, en): ");
        String idioma = teclado.nextLine().trim();

        Idiomas idiomaSeleccionado;
        try {
            idiomaSeleccionado = Idiomas.fromString(idioma);
        } catch (IllegalArgumentException e) {
            System.out.println("Código de idioma no válido.");
            return;
        }

        List<Libro> librosPorIdioma = repositorio.findByIdioma(idiomaSeleccionado);

        System.out.println("----------------------------------");
        System.out.println("Estadísticas para el idioma: " + idiomaSeleccionado.getIdiomaCompleto());
        System.out.println("Cantidad de libros disponibles: " + librosPorIdioma.size());
        System.out.println("----------------------------------");
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

    private void mostrarTop10LibrosDescargados(){
        List<Libro> topLibros = repositorio.findTop10ByOrderByDescargasDesc();
        System.out.println("--Top 10 libros mas descargados--");
        Imprime.imprimirLibros(topLibros);
    }

}
