package com.mcdb.MovieCharacters;

import com.mcdb.MovieCharacters.models.Character;
import com.mcdb.MovieCharacters.models.Franchise;
import com.mcdb.MovieCharacters.models.Movie;
import com.mcdb.MovieCharacters.repositories.CharacterRepository;
import com.mcdb.MovieCharacters.repositories.FranchiseRepository;
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
	private FranchiseRepository franchiseRepository;

	@Autowired
	public MovieCharactersApplication(CharacterRepository characterRepository, MovieRepository movieRepository, FranchiseRepository franchiseRepository) {
		this.characterRepository = characterRepository;
		this.movieRepository = movieRepository;
		this.franchiseRepository = franchiseRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCharactersApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		//Characters
		Character harry = new Character("Harry Potter", "The boy who lived", "Male", "someurl/image/someid.jpg");
		Character dumbledore = new Character("Albus Percival Wulfric Brian Dumbledore", "Dumbledore", "Male", "someurl/image/someid.jpg");
		Character tonyStark = new Character("Tony Stark", "Iron man", "Male", "someurl/image/someid.jpg");

		//Movies
		Movie hp1 = new Movie("Harry Potter and the Sorcerer's Stone", "Adventure", "2001",
				"Chris Colombus", "blablabla.com", "blebleble.se");
		Movie hp2 = new Movie("Harry Potter and the Chamber of Secrets", "Adventure", "2002",
				"Chris Colombus", "blablabla.no", "blebleble.dk");
		Movie mcu = new Movie("The Avengers", "Action", "2012",
				"Joss Whedon", "blablabla.fi", "blebleble.is");

		//Movie - Character relations
		//hp1
		hp1.addCharacterToMovie(harry);
		hp1.addCharacterToMovie(dumbledore);
		harry.addMovieToCharacter(hp1);
		dumbledore.addMovieToCharacter(hp1);
		//hp2
		hp2.addCharacterToMovie(harry);
		hp2.addCharacterToMovie(dumbledore);
		harry.addMovieToCharacter(hp2);
		dumbledore.addMovieToCharacter(hp2);
		//mcu 1
		mcu.addCharacterToMovie(tonyStark);
		tonyStark.addMovieToCharacter(mcu);

		characterRepository.save(harry);
		characterRepository.save(dumbledore);
		characterRepository.save(tonyStark);

		//Franchise
		Franchise harryPotterFranchise = new Franchise("Harry potter", "Harry potter");
		harryPotterFranchise.addMovieToFranchise(hp1);
		harryPotterFranchise.addMovieToFranchise(hp2);
		franchiseRepository.save(harryPotterFranchise);

		//Franchise - Movie relations
		Franchise marvelFranchise = new Franchise("Marvel", "Marvel cinematic universe");
		marvelFranchise.addMovieToFranchise(mcu);
		franchiseRepository.save(marvelFranchise);

		hp1.setFranchise(harryPotterFranchise);
		hp2.setFranchise(harryPotterFranchise);
		mcu.setFranchise(marvelFranchise);

		movieRepository.save(hp1);
		movieRepository.save(hp2);
		movieRepository.save(mcu);
	}
}
