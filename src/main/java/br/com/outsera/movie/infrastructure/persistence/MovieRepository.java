package br.com.outsera.movie.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
	
	Optional<MovieEntity> findByTitle(String name);
	
	@Query(nativeQuery = true, value = "SELECT count(1) FROM movie")
	Integer countMovies();
	
	@Query(nativeQuery = true, value = "SELECT distinct m.id, m.title, m.year_release, m.winner FROM MOVIE m "
			+ "inner join movie_producer mp on mp.movie_id = m.id "
			+ "where m.winner = true and mp.producer_id = :producerId "
			+ "order by m.year_release ")
	Optional<List<MovieEntity>> getMoviesByProducerId(Long producerId);
	
}
