package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ActorWithMoviesTests {

	@Test
	public void test_noReallyMovies_returns_a_list_of_movies() {
		//Arrange
		ArrayList<Movie> movies = new ArrayList<Movie>();
		ActorWithMovies actorWithMovies = new ActorWithMovies();
		actorWithMovies.setMovies(movies);
		
		//Act
		List<Movie> actualMovies = actorWithMovies.noReallyMovies();
		
		//Assert
		assertThat(actualMovies).isSameAs(movies);
		
	}
}
