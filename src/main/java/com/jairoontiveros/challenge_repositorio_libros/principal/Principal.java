package com.jairoontiveros.challenge_repositorio_libros.principal;


public class Principal {


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


        }
    }

}
