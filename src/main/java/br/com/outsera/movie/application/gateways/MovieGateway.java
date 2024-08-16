package br.com.outsera.movie.application.gateways;

import java.util.List;

import br.com.outsera.movie.domain.entity.Movie;

public interface MovieGateway {
	Movie createMovie(Movie movie);
	List<Movie> getMovieList();
	Movie getMovieById(Long id);
	void removeMovie(Long id);
}
