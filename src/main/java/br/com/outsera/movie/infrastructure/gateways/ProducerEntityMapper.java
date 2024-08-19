package br.com.outsera.movie.infrastructure.gateways;

import java.util.ArrayList;
import java.util.List;

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
	
	List<Producer> toDomainList(List<ProducerEntity> resultList) {
		List<Producer> studioList = new ArrayList<Producer>();
		for(ProducerEntity producer: resultList) {
			studioList.add(toDomainObj(producer));
		}
		return studioList;
	}
	
	ProducerEntity toEntityObjUpd(Producer domain) {
		return new ProducerEntity(domain.getId(), domain.getName());
	}
}
