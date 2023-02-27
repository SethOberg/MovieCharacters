package com.mcdb.MovieCharacters;

import com.mcdb.MovieCharacters.models.MovieCharacter;
import com.mcdb.MovieCharacters.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieCharactersApplication implements ApplicationRunner {

	private CharacterRepository characterRepository;

	@Autowired
	public MovieCharactersApplication(CharacterRepository characterRepository) {
		this.characterRepository = characterRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCharactersApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		MovieCharacter test = new MovieCharacter(3, "name", "some alias", "m", "someurl/image/someid.jpg");
		characterRepository.save(test);
	}
}
