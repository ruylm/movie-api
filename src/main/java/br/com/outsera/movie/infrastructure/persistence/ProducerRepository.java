package br.com.outsera.movie.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProducerRepository extends JpaRepository<ProducerEntity, Long> {
	
	ProducerEntity findByName(String name);
	
	@Query(nativeQuery = true, value = "SELECT count(1) FROM movie_producer where producer_id = :id ")
	Integer isLinkedProducerWithMovie(Long id);
	
	@Query(nativeQuery = true, value = "SELECT distinct p.id, p.name FROM MOVIE m "
			+ "inner join movie_producer mp on mp.movie_id = m.id "
			+ "inner join producer p on p.id = mp.producer_id "
			+ "where m.winner = true "
			+ "order by p.id ")
	List<ProducerEntity> getWinningProducers();
	
}
