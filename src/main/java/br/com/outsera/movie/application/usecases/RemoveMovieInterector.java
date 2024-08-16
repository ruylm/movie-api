package br.com.outsera.movie.application.usecases;

import br.com.outsera.movie.application.gateways.MovieGateway;

public class RemoveMovieInterector {
	
	private MovieGateway movieGateway;
	
	public RemoveMovieInterector(MovieGateway movieGateway) {
		this.movieGateway = movieGateway;
	}

	public void removeMovie(Long id) {
		movieGateway.removeMovie(id);
	}
	
}
