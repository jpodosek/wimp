package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

public class MovieApiControllerTests {

	private MovieRepository movieRepo;
	private ActorRepository actorRepo;
	private MovieApiController movieController;
	
	
	@Before
	public void setUp() {
		//use mock to tell it what you want to happen; verify down below
		movieRepo = mock(MovieRepository.class);
		actorRepo = mock(ActorRepository.class);
		movieController = new MovieApiController(movieRepo, actorRepo);
	}
	
	@Test
	public void test_getAll_returns_all_Movies_returned_by_the_repo() {
		// Arrange
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		movies.add(new Movie());
		
		when(movieRepo.findAll()).thenReturn(movies);
	
		// Act
		//what actually gets returned
		List<Movie> actual = movieController.getAll();
		
		// Assert
		//.isSameAs checks that two objects are the same object, not the same value
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(movies.get(0));
		verify(movieRepo).findAll(); //verify that mock is the mock you've created with mockito
									//and method findAll() has been called;
		
	}
	
	@Test //do not expect this to fail
	public void test_getOne_returns_one_Movie_returned_from_repo() throws StuffNotFoundException {
		// Arrange
		Movie savingPrivateRyan = new Movie();
		
		//mock pretends to have a repository that talks to database
		//objects don't need actual data
		when(movieRepo.findOne(22L)).thenReturn(savingPrivateRyan);
		
		// Act
		Movie actual = movieController.getOne(22L);
		
		// Assert
		assertThat(actual).isSameAs(savingPrivateRyan);
		verify(movieRepo).findOne(22L);
	}
	
	@Test
	//expectation for this test is an exception is thrown; we need to catch one
	public void test_getOne_throws_StuffNotFound_when_no_movie_returned_from_repo() {
		try {
			movieController.getOne(1);
			//asserting that this failed
			fail("The movieController did not throw the StuffNotFoundException");	
		} catch(StuffNotFoundException snfe) {}
	}
	
	@Test
	public void test_deleteOne_returns_movie_deleted_when_found() {
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(3L)).thenReturn(movie);
		
		//Act
		Movie actual = movieController.deleteOne(3L);
		
		//Assert
		assertThat(actual).isSameAs(movie);
		verify(movieRepo).delete(3L);
		verify(movieRepo).findOne(3L);	
	}	
	
	@Test
	public void test_that_null_is_returned_when_findOne_throwsEmptyResultDataAccess() throws StuffNotFoundException {
		//Arrange
		doThrow(new EmptyResultDataAccessException(0)).when(movieRepo).delete(8L);
		
		//Act
		Movie actual = movieController.deleteOne(8L);
		
		//Assert
		assertThat(actual).isNull();
		verify(movieRepo).delete(8L);
		
	}
	
	@Test
	public void test_that_creates_a_new_Movie_and_saves_it_to_MovieRepo() {
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);
		
		//Act - the actual method you are testing
		Movie actualMovie = movieController.create(movie);
		
		//Assert
		assertThat(movie).isSameAs(actualMovie);
		verify(movieRepo).save(movie); //verify that method got called with this exact argument		
	}
	
	@Test
	public void test_update_sets_movie_id_and_saves_to_Movie_Repo() {
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);
		
		//Act
		Movie actualMovie = movieController.update(movie, 3L);
		
		//Assert
		assertThat(actualMovie.getId()).isSameAs(movie.getId()); //test that id was updated	
		verify(movieRepo).save(movie); //verify that method got called with this exact argument		
	}	
	
	
	@Test
	public void test_associateAnActor_adds_an_actor_to_a_movie() {
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(3L)).thenReturn(movie);
		Actor actor = new Actor();
		actor.setId(2L);
		when(actorRepo.findOne(2L)).thenReturn(actor);
		
		//Act
		movie = movieController.associateAnActor(3L, actor);
		
		//Assert
		verify(movieRepo).save(movie); //verify that method got called with this exact argument		
		verify(movieRepo).findOne(3L);
		verify(actorRepo).findOne(2L);		
	}

}
