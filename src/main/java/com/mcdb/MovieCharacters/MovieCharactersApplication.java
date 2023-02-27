package com.mcdb.MovieCharacters;

import com.mcdb.MovieCharacters.models.Character;
import com.mcdb.MovieCharacters.models.Movie;
import com.mcdb.MovieCharacters.repositories.CharacterRepository;
import com.mcdb.MovieCharacters.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;

@SpringBootApplication
public class MovieCharactersApplication implements ApplicationRunner {

	private CharacterRepository characterRepository;
	private MovieRepository movieRepository;

	@Autowired
	public MovieCharactersApplication(CharacterRepository characterRepository, MovieRepository movieRepository) {
		this.characterRepository = characterRepository;
		this.movieRepository = movieRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCharactersApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Character characterTest = new Character(3, "name", "some alias", "m", "someurl/image/someid.jpg", new HashSet<>());
		characterRepository.save(characterTest);
		Movie movieTest = new Movie(1, "Harry Potter", "Adventure", "1999", "Mike Newell", "blablabla.com", "blebleble.se");
		movieRepository.save(movieTest);
	}




}
