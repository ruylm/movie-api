package br.com.outsera.movie.infrastructure.gateways;

import java.util.ArrayList;
import java.util.List;

import br.com.outsera.movie.domain.entity.Movie;
import br.com.outsera.movie.infrastructure.persistence.MovieEntity;

public class MovieEntityMapper {
	
	MovieEntity toEntityObj(Movie movieDomain) {
		return new MovieEntity(movieDomain.getTitle(), movieDomain.getYear(), movieDomain.isWinner(), movieDomain.getStudios(), movieDomain.getProducers());
	}

	Movie toDomainObj(MovieEntity movieEntity) {
		return new Movie(movieEntity.getId(), movieEntity.getTitle(), movieEntity.getYear(), movieEntity.isWinner(), movieEntity.getStudios(), movieEntity.getProducers());
	}
	
	List<Movie> toDomainList(List<MovieEntity> resultList) {
		List<Movie> movieList = new ArrayList<Movie>();
		for(MovieEntity movie: resultList) {
			movieList.add(toDomainObj(movie));
		}
		return movieList;
	}
}
