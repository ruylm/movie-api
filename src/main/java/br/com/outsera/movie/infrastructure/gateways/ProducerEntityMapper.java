package br.com.outsera.movie.infrastructure.gateways;

import br.com.outsera.movie.domain.entity.Producer;
import br.com.outsera.movie.infrastructure.persistence.ProducerEntity;

public class ProducerEntityMapper {
	
	ProducerEntity toEntityObj(Producer domain) {
		return new ProducerEntity(domain.getName());
	}

	Producer toDomainObj(ProducerEntity entity) {
		if(entity == null) {
			return null;
		}
		return new Producer(entity.getId(), entity.getName());
	}
}
