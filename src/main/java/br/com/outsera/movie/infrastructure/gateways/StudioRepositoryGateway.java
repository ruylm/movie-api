package br.com.outsera.movie.infrastructure.gateways;

import java.util.List;
import java.util.Optional;

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
	public Studio create(Studio studioDomain) {
		StudioEntity studioEntity = studioEntityMapper.toEntityObj(studioDomain);
		StudioEntity savedObj = studioRepository.save(studioEntity);
		return studioEntityMapper.toDomainObj(savedObj);
	}

	@Override
	public Studio findByName(Studio studioDomain) {
		StudioEntity result = studioRepository.findByName(studioDomain.getName());
		return studioEntityMapper.toDomainObj(result);
	}
	
	@Override
	public List<Studio> getAll() {
		List<StudioEntity> result = studioRepository.findAll();
		return studioEntityMapper.toDomainList(result);
	}

	@Override
	public Studio getById(Long id) {
		Optional<StudioEntity> result = studioRepository.findById(id);
		if(result.isPresent()) {
			return studioEntityMapper.toDomainObj(result.get());
		}
		return null;
	}

	@Override
	public void remove(Long id) {
		studioRepository.deleteById(id);
	}


	@Override
	public boolean isLinkedStudioWithMovie(Long id) {
		int result = studioRepository.isLinkedStudioWithMovie(id);
		return result > 0 ? true : false;
	}

	@Override
	public Studio update(Studio studio) {
		StudioEntity producerEntity = studioEntityMapper.toEntityObjUpd(studio);
		StudioEntity updatedObj = studioRepository.save(producerEntity);
		return studioEntityMapper.toDomainObj(updatedObj);
	}

}
