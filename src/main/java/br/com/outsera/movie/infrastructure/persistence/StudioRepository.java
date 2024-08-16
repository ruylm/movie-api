package br.com.outsera.movie.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<StudioEntity, Long> {
	
	StudioEntity findByName(String name);

}
