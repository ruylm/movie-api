package br.com.outsera.movie.infrastructure.gateways;

import br.com.outsera.movie.application.gateways.StudioGateway;
import br.com.outsera.movie.domain.entity.Studio;
import br.com.outsera.movie.infrastructure.persistence.StudioEntity;
import br.com.outsera.movie.infrastructure.persistence.StudioRepository;

public class StudioRepositoryGateway implements StudioGateway {
	
	private final StudioRepository studioRepository;
	private final StudioEntityMapper studioEntityMapper;
	
	public StudioRepositoryGateway(StudioRepository studioRepository, StudioEntityMapper studioEntityMapper) {
		this.studioRepository = studioRepository;
		this.studioEntityMapper = studioEntityMapper;
	}


	@Override
	public Studio createStudio(Studio studioDomain) {
		StudioEntity studioEntity = studioEntityMapper.toEntityObj(studioDomain);
		StudioEntity savedObj = studioRepository.save(studioEntity);
		return studioEntityMapper.toDomainObj(savedObj);
	}


	@Override
	public Studio findStudioByName(Studio studioDomain) {
		StudioEntity result = studioRepository.findByName(studioDomain.getName());
		return studioEntityMapper.toDomainObj(result);
	}

}
