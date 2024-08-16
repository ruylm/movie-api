package br.com.outsera.movie.infrastructure.gateways;

import br.com.outsera.movie.domain.entity.Studio;
import br.com.outsera.movie.infrastructure.persistence.StudioEntity;

public class StudioEntityMapper {
	
	StudioEntity toEntityObj(Studio domain) {
		return new StudioEntity(domain.getName());
	}

	Studio toDomainObj(StudioEntity entity) {
		if(entity == null) {
			return null;
		}
		return new Studio(entity.getId(), entity.getName());
	}
}
