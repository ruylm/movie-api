package br.com.outsera.movie.application.usecases;

import java.util.List;

import br.com.outsera.movie.application.gateways.MovieGateway;
import br.com.outsera.movie.domain.entity.Movie;

public class GetMovieListInterector {
	
	private MovieGateway movieGateway;
	
	public GetMovieListInterector(MovieGateway movieGateway) {
		this.movieGateway = movieGateway;
	}

	public List<Movie> getMovieList() {
		return movieGateway.getMovieList();
	}
	
}
