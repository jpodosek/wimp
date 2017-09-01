package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;

public class ActorTests {

	private Actor actor;
	private Movie movie;
	
	@Before
	public void setUp() {
		actor = new Actor();
		movie = new Movie();
	}
	
	@Test
	public void test_Actor_constructor_sets_firstName_lastName() {	
		//Act
		String firstName = "Curtis";
		String lastName = "Schlak";
		Date birthDate = new Date(Date.parse("12/31/19"));
		Actor actualActor = new Actor(firstName, lastName, birthDate);
			
		//Assert
		assertThat(actualActor.getFirstName()).isSameAs(firstName);
		assertThat(actualActor.getLastName()).isSameAs(lastName);
		assertThat(actualActor.getBirthDate()).isSameAs(birthDate);
	}
	
	
	@Test
	public void test_Actor_constructor_sets_firstName_lastName_birthDate() {	
		//Act
		String firstName = "Curtis";
		String lastName = "Schlak";
		Actor actualActor = new Actor(firstName, lastName);
			
		//Assert
		assertThat(actualActor.getFirstName()).isSameAs(firstName);
		assertThat(actualActor.getLastName()).isSameAs(lastName);
	}
	

	@Test
	public void test_getMovies_and_setMovies() {
		//Arrange
		ArrayList<Movie> movies = new ArrayList<Movie>();
		actor.setMovies(movies);
		
		//Act
		List<Movie> actualMovies = actor.getMovies();
		
		//Assert
		assertThat(actualMovies).isSameAs(movies);		
	}
	
	@Test
	public void test_setId_and_getId_work() {
		//Arrange
		Long id = 4L;
		actor.setId(id);
		//Act
		Long actualId = actor.getId();			
		//Assert
		assertThat(actualId).isSameAs(id);
	}
	
	@Test
	public void test_setfirstName_and_getfirstName_work() {
		//Arrange
		String firstName = "amanda";
		actor.setFirstName(firstName);
		//Act
		String actualFirstName = actor.getFirstName();			
		//Assert
		assertThat(actualFirstName).isSameAs(firstName);
	}
	
	@Test
	public void test_setlastName_and_getlastName_work() {
		//Arrange
		String lastName = "amandasLastName";
		actor.setLastName(lastName);
		//Act
		String actualLastName = actor.getLastName();			
		//Assert
		assertThat(actualLastName).isSameAs(lastName);
	}
	
	@Test
	public void test_setActiveSinceYear_and_getActiveSinceYear_work() {
		//Arrange
		Long year = 4L;
		actor.setActiveSinceYear(year);
		//Act
		Long actualYear = actor.getActiveSinceYear();			
		//Assert
		assertThat(actualYear).isSameAs(year);
	}
	
	
	@Test
	public void test_setBirthDate_and_getBirthDate_work() {
		//Arrange
		Date birthDate = new Date(Date.parse("12/31/19"));
		actor.setBirthDate(birthDate);
		//Act
		Date actualBirthDate = actor.getBirthDate();			
		//Assert
		assertThat(actualBirthDate).isSameAs(birthDate);
	}
	

	@Test
	public void test_getAwards_and_setAwards() {
		//Arrange
		ArrayList<Award> awards = new ArrayList<Award>();
		actor.setAwards(awards);
		
		//Act
		List<Award> actualAwards = actor.getAwards();
		
		//Assert
		assertThat(actualAwards).isSameAs(awards);
		
	}
	
//	@Test
//	public void test_addWard_adds_an_Award_to_AwardsArrayList() {
//		//Arrange
//		ArrayList<Award> awards = new ArrayList<Award>();
//		Award award1 = new Award();
//		awards.add(award1);
//			
//		//Act
//		awards = actor.addAward(award1);;
//		
//		//Assert
//		assertThat(actualAwards.get(0)).isSameAs(awards.get(0));
//	}

}
