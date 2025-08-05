package com.jairoontiveros.challenge_repositorio_libros;

import com.jairoontiveros.challenge_repositorio_libros.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeRepositorioLibrosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeRepositorioLibrosApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraMenu();
	}
}
