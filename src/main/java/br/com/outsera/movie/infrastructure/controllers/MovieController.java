package br.com.outsera.movie.infrastructure.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.outsera.movie.application.usecases.CreateMovieInterector;
import br.com.outsera.movie.application.usecases.GetIntervalAwardsInterector;
import br.com.outsera.movie.application.usecases.GetMovieByIdInterector;
import br.com.outsera.movie.application.usecases.GetMovieListInterector;
import br.com.outsera.movie.application.usecases.RemoveMovieInterector;
import br.com.outsera.movie.domain.entity.IntervalAwards;
import br.com.outsera.movie.domain.entity.Movie;
import br.com.outsera.movie.infrastructure.dto.CreateMovieRequest;
import br.com.outsera.movie.infrastructure.dto.CreateMovieResponse;
import br.com.outsera.movie.infrastructure.dto.GetIntervalAwardsResponse;
import br.com.outsera.movie.infrastructure.dto.GetMovieResponse;
import br.com.outsera.movie.infrastructure.dto.IntervalAwardsDTOMapper;
import br.com.outsera.movie.infrastructure.dto.MovieDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Movie", description = "Movie management APIs")
@RestController
@RequestMapping("/movies")
public class MovieController {
	
	private final CreateMovieInterector createMovieInterector;
	private final GetMovieListInterector getMovieListInterector;
	private final GetMovieByIdInterector getMovieByIdInterector;
	private final GetIntervalAwardsInterector getIntervalAwardsInterector;
	private final RemoveMovieInterector removeMovieInterector;
	private final MovieDTOMapper movieDTOMapper;
	private final IntervalAwardsDTOMapper intervalAwardsDTOMapper;

	public MovieController(CreateMovieInterector createMovieInterector, GetMovieListInterector getMovieListInterector, GetIntervalAwardsInterector getIntervalAwardsInterector,
			GetMovieByIdInterector getMovieByIdInterector, RemoveMovieInterector removeMovieInterector, MovieDTOMapper movieDTOMappe, IntervalAwardsDTOMapper intervalAwardsDTOMapper) {
		this.createMovieInterector = createMovieInterector;
		this.getMovieListInterector = getMovieListInterector;
		this.getMovieByIdInterector = getMovieByIdInterector;
		this.getIntervalAwardsInterector = getIntervalAwardsInterector;
		this.removeMovieInterector = removeMovieInterector;
		this.movieDTOMapper = movieDTOMappe;
		this.intervalAwardsDTOMapper = intervalAwardsDTOMapper;
	}
	
	
	@Operation(summary = "Create a movie")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "201", description = "Movie created", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CreateMovieResponse.class)) })})
	@PostMapping
	public ResponseEntity<CreateMovieResponse> create(@RequestBody CreateMovieRequest request) {
		Movie movieBusinessObj = movieDTOMapper.toMovie(request);
		Movie movie = createMovieInterector.createMovie(movieBusinessObj);
		return ResponseEntity.status(HttpStatus.CREATED).body(movieDTOMapper.toResponse(movie));
	}
	
	@Operation(summary = "Retrieves a list of films")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CreateMovieResponse.class)) })})
	@GetMapping
	public ResponseEntity<List<GetMovieResponse>> getMovieList() {
		List<Movie> movieList = getMovieListInterector.getMovieList();
		return ResponseEntity.status(HttpStatus.OK).body(movieDTOMapper.toResponseList(movieList));
	}
	
	@Operation(summary = "Retrieve a movie by Id")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CreateMovieResponse.class)) }),
		  @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<GetMovieResponse> getMovieById(@PathVariable("id") Long id) {
		Movie movie = getMovieByIdInterector.getMovieById(id);
		if(movie == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} 
		return ResponseEntity.status(HttpStatus.OK).body(movieDTOMapper.toGetMovieResponse(movie));
	}
	
	@Operation(summary = "Retrieve a list of winners in the Worst Film category")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = GetIntervalAwardsResponse.class)) }),
		  @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content) })
	@GetMapping("/interval-awards")
	public ResponseEntity<GetIntervalAwardsResponse> getIntervalAwards() {
		IntervalAwards intervalAwards = getIntervalAwardsInterector.getIntervalAwards();
		if(intervalAwards == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(intervalAwardsDTOMapper.toGetIntervalAwardsResponse(intervalAwards));
	}

	@Operation(summary = "Delete a movie by Id")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "204", content = @Content),
		  @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeMovie(@PathVariable("id") Long id) {
		Movie movieList = getMovieByIdInterector.getMovieById(id);
		if(movieList == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		removeMovieInterector.removeMovie(id);
		return ResponseEntity.noContent().build();
	}
	
}
