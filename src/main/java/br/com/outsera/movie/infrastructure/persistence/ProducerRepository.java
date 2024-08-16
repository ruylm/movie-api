package br.com.outsera.movie.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<ProducerEntity, Long> {
	
	ProducerEntity findByName(String name);

}
