package br.com.outsera.movie.infrastructure.gateways;

import java.util.ArrayList;
import java.util.List;

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
	
	List<Studio> toDomainList(List<StudioEntity> resultList) {
		List<Studio> studioList = new ArrayList<Studio>();
		for(StudioEntity studio: resultList) {
			studioList.add(toDomainObj(studio));
		}
		return studioList;
	}
	
	StudioEntity toEntityObjUpd(Studio domain) {
		return new StudioEntity(domain.getId(), domain.getName());
	}
	
}
