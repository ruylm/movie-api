package br.com.outsera.movie.infrastructure.gateways;

import java.util.List;
import java.util.Optional;

import br.com.outsera.movie.application.gateways.MovieGateway;
import br.com.outsera.movie.domain.entity.Movie;
import br.com.outsera.movie.infrastructure.persistence.MovieEntity;
import br.com.outsera.movie.infrastructure.persistence.MovieRepository;

public class MovieRepositoryGateway implements MovieGateway {
	
	private final MovieRepository movieRepository;
	private final MovieEntityMapper movieEntityMapper;
	
	public MovieRepositoryGateway(MovieRepository movieRepository, MovieEntityMapper movieEntityMapper) {
		this.movieRepository = movieRepository;
		this.movieEntityMapper = movieEntityMapper;
	}


	@Override
	public Movie createMovie(Movie movieDomain) {
		MovieEntity movieEntity = movieEntityMapper.toEntityObj(movieDomain);
		MovieEntity savedObj = movieRepository.save(movieEntity);
		return movieEntityMapper.toDomainObj(savedObj);
	}


	@Override
	public List<Movie> getMovieList() {
		List<MovieEntity> result = movieRepository.findAll();
		return movieEntityMapper.toDomainList(result);
	}


	@Override
	public Movie getMovieById(Long id) {
		Optional<MovieEntity> result = movieRepository.findById(id);
		if(result.isPresent()) {
			return movieEntityMapper.toDomainObj(result.get());
		}
		return null;
	}


	@Override
	public void removeMovie(Long id) {
		movieRepository.deleteById(id);
	}

}
