package com.libertymutual.goforcode.wimp.api;

import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.ActorWithMovies;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;

@RestController
@RequestMapping("/api/actors")
public class ActorApiController {

	
	private ActorRepository actorRepo;
	private AwardRepository awardRepo;
	
	public ActorApiController(ActorRepository actorRepo, AwardRepository awardRepo) {
		this.actorRepo = actorRepo;
		this.awardRepo = awardRepo;
		actorRepo.save(new Actor("Brad", "Pitt"));
		actorRepo.save(new Actor("Jonah", "Hill"));
		actorRepo.save(new Actor("Curtis", "Schlak", new Date(Date.parse("8/23/1900"))));	
	}
	
	@GetMapping("")
	public List<Actor> getAll() {
		return actorRepo.findAll();
	}
	
	@GetMapping("{id}") // naming {id} the same as pathvariable connects them
	public Actor getOne(@PathVariable long id) throws StuffNotFoundException {
		Actor actor = actorRepo.findOne(id);
		if (actor == null) {
			throw new StuffNotFoundException();
		}
		
//		ActorWithMovies newActor = new ActorWithMovies();
//		newActor.setActiveSinceYear(actor.getActiveSinceYear());
//		newActor.setBirthDate(actor.getBirthDate());
//		newActor.setMovies(actor.getMovies());
//		newActor.setFirstName(actor.getFirstName());
//		newActor.setLastName(actor.getLastName());
//		return newActor;
		
		return actor;
	}
	
	@DeleteMapping("{id}")
	public Actor deleteOne(@PathVariable long id) {
		try {
			Actor actor = new Actor();
			actorRepo.delete(id);
			return actor;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("trying to re-delete an id");
			return null;
		}
	}
	
	@PostMapping("") //@RequestBody will bind any JSON object cereal variable
	public Actor create(@RequestBody Actor actor) {
		return actorRepo.save(actor);
	}
	
	
	@PutMapping("{id}")
	 public Actor update(@RequestBody Actor actor, @PathVariable long id) {
		actor.setId(id);
	 return actorRepo.save(actor);
	 }
	
	@PostMapping("{actorId}/awards")
	public Actor createAwardEntityAndAssociateToActor(@PathVariable long actorId, @RequestBody Award award) {
		Actor actor = actorRepo.findOne(actorId);
		
		//award = awardRepo.findOne(award.getId());
		
		Award newaward = new Award(award.getTitle(), award.getOrganization(), award.getYear());
		awardRepo.save(newaward);
		actor.addAward(newaward);
		actorRepo.save(actor);
		//awardRepo.save(new Award("Best Actor" , "Oscar Academy", 2008));

//	public Movie associateAnActor(@PathVariable long movieId, @RequestBody Actor actor) {
//	Movie movie = movieRepo.findOne(movieId);
//	actor = actorRepo.findOne(actor.getId());
//	
//	movie.addActor(actor);
//	movieRepo.save(movie); //this should insert record in join table
//	return movie;
		return actor;
}

}
