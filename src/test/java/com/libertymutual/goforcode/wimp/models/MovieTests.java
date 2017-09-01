package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MovieTests {

	private Actor actor;
	private Movie movie;
	
	@Before
	public void setUp() {
		actor = new Actor();
		movie = new Movie();
	}
	
	@Test
	public void test_Movie_constructor_sets_title_distributor() {	
		//Act
		String title = "Fight Club";
		String distributor = "Warner Bros";
		Movie movie = new Movie(title, distributor);
			
		//Assert
		assertThat(movie.getTitle()).isSameAs(title);
		assertThat(movie.getDistributor()).isSameAs(distributor);
	}
	
	
	@Test
	public void test_getActors_and_setActors() {
		//Arrange
		ArrayList<Actor> actors = new ArrayList<Actor>();
		movie.setActors(actors);
		
		//Act
		List<Actor> actualActors = movie.getActors();
		
		//Assert
		assertThat(actualActors).isSameAs(actors);		
	}
	
	
	
	@Test
	public void test_setId_and_getId_work() {
		//Arrange
		Long id = 4L;
		movie.setId(id);
		//Act
		Long actualId = movie.getId();			
		//Assert
		assertThat(actualId).isSameAs(id);
	}
	
	@Test
	public void test_setTitle_and_getTitle_work() {
		//Arrange
		String title = "Fight Club";
		movie.setTitle(title);
		//Act
		String actualTitle = movie.getTitle();			
		//Assert
		assertThat(actualTitle).isSameAs(title);
	}
	
	@Test
	public void test_setReleaseDate_and_getReleaseDate_work() {
		//Arrange
		Date releaseDate = new Date(Date.parse("12/31/19"));
		movie.setReleaseDate(releaseDate);
		//Act
		Date actualReleaseDate = movie.getReleaseDate();			
		//Assert
		assertThat(actualReleaseDate).isSameAs(releaseDate);
	}

	@Test
	public void test_setBudget_and_getBudget_work() {
		//Arrange
		Long budget = 4000000L;
		movie.setBudget(budget);
		//Act
		Long actualBudget = movie.getBudget();			
		//Assert
		assertThat(actualBudget).isSameAs(budget);
	}
	
	@Test
	public void test_setDistributor_and_getDistributor_work() {
		//Arrange
		String distributor = "Warner Bros";
		movie.setDistributor(distributor);
		//Act
		String actualDistributor = movie.getDistributor();			
		//Assert
		assertThat(actualDistributor).isSameAs(distributor);
	}
	
	@Test
	public void test_addActor_adds_an_actor_to_ActorList_from_movie() {
		//Arrange
		Actor actor = new Actor();
		
		//Act
		movie.addActor(actor);
		
		//Assert
		assertThat(movie.getActors().contains(actor));
	}	
}
