package com.jairoontiveros.challenge_repositorio_libros.service;

public interface IConvierteDatos {
        <T> T mapeaDatos(String json, Class<T> clase);
}
