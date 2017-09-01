package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;

public class ActorApiControllerTests {

	private ActorRepository actorRepo;
	private AwardRepository awardRepo;
	private ActorApiController actorController;

	
	@Before
	public void setUp() {
		//use mock to tell it what you want to happen; verify down below
		actorRepo = mock(ActorRepository.class);
		awardRepo = mock(AwardRepository.class);
		actorController = new ActorApiController(actorRepo, awardRepo);
	}
	
	@Test
	public void test_getAll_returns_all_Actors_returned_by_the_repo() {
		// Arrange
		ArrayList<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		actors.add(new Actor());
		
		when(actorRepo.findAll()).thenReturn(actors);
	
		// Act
		//what actually gets returned
		List<Actor> actual = actorController.getAll();
		
		// Assert
		//.isSameAs checks that two objects are the same object, not the same value
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(actors.get(0));
		verify(actorRepo).findAll(); //verify that mock is the mock you've created with mockito
									//and method findAll() has been called;
	}
	
	@Test //do not expect this to fail
	public void test_getOne_returns_one_Actor_returned_from_repo() throws StuffNotFoundException {
		// Arrange
		Actor tomHanks = new Actor();
		
		//mock pretends to have a repository that talks to database
		//objects don't need actual data
		when(actorRepo.findOne(22L)).thenReturn(tomHanks);
		
		// Act
		Actor actual = actorController.getOne(22L);
		
		// Assert
		assertThat(actual).isSameAs(tomHanks);
		verify(actorRepo).findOne(22L);
	}
	
	@Test
	//expectation for this test is an exception is thrown; we need to catch one
	public void test_getOne_throws_StuffNotFound_when_no_Actor_returned_from_repo() {
		try {
			actorController.getOne(1);
			//asserting that this failed
			fail("The actorController did not throw the StuffNotFoundException");	
		} catch(StuffNotFoundException snfe) {}
	}
	
	@Test
	public void test_delete_returns_movie_deleted_when_found() {
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(3L)).thenReturn(actor);
		
		//Act
		Actor actual = actorController.deleteOne(3L);
		
		//Assert
		assertThat(actor).isSameAs(actual);
		verify(actorRepo).delete(3L);
		verify(actorRepo).findOne(3L);
		
	}
	
	@Test
	public void test_that_null_is_returned_when_findOne_throwsEmptyResultDataAccess() throws StuffNotFoundException {
		//Arrange
		doThrow(new EmptyResultDataAccessException(0)).when(actorRepo).delete(8L);
		
		//Act
		Actor actual = actorController.deleteOne(8L);
		
		//Assert
		assertThat(actual).isNull();
		verify(actorRepo).delete(8L);
		
	}
	
	@Test
	public void test_that_creates_a_new_Actor_and_saves_it_to_ActorRepo() {
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		
		//Act - the actual method you are testing
		Actor actualActor = actorController.create(actor);
		
		//Assert
		assertThat(actor).isSameAs(actualActor);
		verify(actorRepo).save(actor); //verify that method got called with this exact argument		
	}
	
	
	@Test
	public void test_update_sets_movie_id_and_saves_to_Movie_Repo() {
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		
		//Act
		Actor actualActor = actorController.update(actor, 3L);
		
		//Assert
		assertThat(actualActor.getId()).isSameAs(actor.getId());
		verify(actorRepo).save(actor); //verify that method got called with this exact argument		
	}
	
	
	@Test
	public void test_createAwardEntityAndAssociateToActor() {
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(3L)).thenReturn(actor);
		Award award = new Award("Best Picture", "Academy", 2008);
		//actor.addAward(award);
		
		//Act
		actor = actorController.createAwardEntityAndAssociateToActor(3L, award);
		//Assert
		verify(actorRepo).save(actor); //verify that method got called with this exact argument	
		verify(actorRepo).findOne(3L);
		assertThat(actor.getAwards().contains(0));
		
	}
	
}
