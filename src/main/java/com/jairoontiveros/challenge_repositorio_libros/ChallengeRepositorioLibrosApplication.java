package com.jairoontiveros.challenge_repositorio_libros;

import com.jairoontiveros.challenge_repositorio_libros.principal.Principal;
import com.jairoontiveros.challenge_repositorio_libros.repositorio.RepositorioAutor;
import com.jairoontiveros.challenge_repositorio_libros.repositorio.RepositorioLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeRepositorioLibrosApplication implements CommandLineRunner {

	@Autowired
	private RepositorioLibro repository;
	@Autowired
	private RepositorioAutor repositoryAutor;


	public static void main(String[] args) {
		SpringApplication.run(ChallengeRepositorioLibrosApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository,repositoryAutor);
		principal.muestraMenu();
	}
}
