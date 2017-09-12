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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/movies")
@Api(description = "Use this to create/get movies, and add actors to movies.")
public class MovieApiController {

	private MovieRepository movieRepo;
	private ActorRepository actorRepo;

	public MovieApiController(MovieRepository movieRepo, ActorRepository actorRepo) {
		this.movieRepo = movieRepo;
		this.actorRepo = actorRepo;
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
			Movie movie = movieRepo.findOne(id);
			movieRepo.delete(id);
			return movie;
		} catch (EmptyResultDataAccessException erdae) {
			System.err.println("trying to re-delete an id");
			return null;
		}
	}

	@PostMapping("") // @RequestBody will bind any JSON object cereal variable
	public Movie create(@RequestBody Movie movie) {
		return movieRepo.save(movie);
	}

	@PutMapping("{id}") //id in URL Is the movie you want to update using json
	public Movie update(@RequestBody Movie movie, @PathVariable long id) {
		movie.setId(id);
		return movieRepo.save(movie);
	}

	@ApiOperation(value = "Add an actor to the cast of a movie", notes = "You only need to POSt the \"id\" of the actor, not the whole entity.")
	@PostMapping("{movieId}/actors") // creates an association between movies and an actor
										// add actorId via JSON
	public Movie associateAnActor(@PathVariable long movieId, @RequestBody Actor actor) {
		Movie movie = movieRepo.findOne(movieId);
		Long actorId = actor.getId();
		actor = actorRepo.findOne(actorId);

		movie.addActor(actor);
		movieRepo.save(movie); // this should insert record in join table
		return movie;

	}

}
