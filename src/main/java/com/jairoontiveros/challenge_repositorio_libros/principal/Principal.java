package com.jairoontiveros.challenge_repositorio_libros.principal;


import com.jairoontiveros.challenge_repositorio_libros.model.DatosLibros;
import com.jairoontiveros.challenge_repositorio_libros.model.Libro;
import com.jairoontiveros.challenge_repositorio_libros.model.Resultados;
import com.jairoontiveros.challenge_repositorio_libros.service.APIService;
import com.jairoontiveros.challenge_repositorio_libros.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/";
    private APIService apiService = new APIService();
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<Libro> librosGuardados = new ArrayList<>();


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
                    break;
                case 4:
                    break;
                case 5:
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
        //lista para guardar libros buscados temporal

        librosGuardados.add(libro);
        return libro;

    }

    public void mostrarLibrosRegistrados() {
        if (librosGuardados.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }

        librosGuardados.forEach(System.out::println);
    }



}
