package com.libertymutual.goforcode.wimp.api;

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
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;


@RestController
@RequestMapping("/api/movies")
public class MovieApiController {

private MovieRepository movieRepo;
private ActorRepository actorRepo;

	public MovieApiController(MovieRepository movieRepo, ActorRepository actorRepo) {
		this.movieRepo = movieRepo;
		this.actorRepo = actorRepo;
		
		movieRepo.save(new Movie("Inglorious Bastards", "Miramax"));
		movieRepo.save(new Movie("Fight Club", "Warner Brothers"));
		movieRepo.save(new Movie("Fury", "Warner Brothers"));		
		movieRepo.save(new Movie("Moneyball", "Warner Brothers"));		
	}
	
	@GetMapping("")
	public List<Movie> getAll() {
		return movieRepo.findAll();
	}
	
	@GetMapping("{id}") // naming {id} the same as pathvariable connects them
	public Movie getOne(@PathVariable long id) throws StuffNotFoundException {
		Movie movie = movieRepo.findOne(id);
		if (movie == null) {
			throw new StuffNotFoundException();
		}
		return movie;
	}
	
	
	@DeleteMapping("{id}")
	public Movie deleteOne(@PathVariable long id) {
		try {
			Movie movie = new Movie();
			movieRepo.delete(id);
			return movie;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("trying to re-delete an id");
			return null;
		}
	}
	
	@PostMapping("") //@RequestBody will bind any JSON object cereal variable
	public Movie create(@RequestBody Movie movie) {
		return movieRepo.save(movie);
	}
	
	
	@PostMapping("{movieId}/actors") //creates an association between movies and an actor
									//add actorId via JSON
	public Movie associateAnActor(@PathVariable long movieId, @RequestBody Actor actor) {
		Movie movie = movieRepo.findOne(movieId);
		actor = actorRepo.findOne(actor.getId());
		
		movie.addActor(actor);
		movieRepo.save(movie); //this should insert record in join table
		return movie;
	}
		
	@PutMapping("{id}")
	 public Movie update(@RequestBody Movie movie, @PathVariable long id) {
		movie.setId(id);
	 return movieRepo.save(movie);
	 }
	
}
