package br.com.outsera.movie.application.gateways;

import java.util.List;

import br.com.outsera.movie.domain.entity.Movie;

public interface MovieGateway {
	
	public Movie createMovie(Movie movie);
	public List<Movie> getMovieList();
	public Movie getMovieById(Long id);
	public Movie getMovieByTitle(String title);
	public Integer getCountMovies();
	public void removeMovie(Long id);
	public List<Movie> getMoviesByProducerId(Long id);
	
}
