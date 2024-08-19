package br.com.outsera.movie.application.usecases;

import br.com.outsera.movie.application.gateways.MovieGateway;
import br.com.outsera.movie.domain.entity.Movie;

public class GetMovieByIdInterector {
	
	private MovieGateway movieGateway;
	
	public GetMovieByIdInterector(MovieGateway movieGateway) {
		this.movieGateway = movieGateway;
	}

	public Movie getMovieById(Long id) {
		return movieGateway.getMovieById(id);
	}
	
	public Movie getMovieByTitle(String title) {
		return movieGateway.getMovieByTitle(title);
	}
	
	public Integer getCountMovies() {
		return movieGateway.getCountMovies();
	}
	
}
