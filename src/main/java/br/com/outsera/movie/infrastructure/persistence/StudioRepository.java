package br.com.outsera.movie.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudioRepository extends JpaRepository<StudioEntity, Long> {
	
	StudioEntity findByName(String name);
	
	@Query(nativeQuery = true, value = "SELECT count(1) FROM movie_studio where studio_id = :id ")
	Integer isLinkedStudioWithMovie(Long id);

}
