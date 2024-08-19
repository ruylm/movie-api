package br.com.outsera.movie.infrastructure.gateways;

import java.util.List;
import java.util.Optional;

import br.com.outsera.movie.application.gateways.ProducerGateway;
import br.com.outsera.movie.domain.entity.Producer;
import br.com.outsera.movie.infrastructure.persistence.ProducerEntity;
import br.com.outsera.movie.infrastructure.persistence.ProducerRepository;

public class ProducerRepositoryGateway implements ProducerGateway {
	
	private final ProducerRepository producerRepository;
	private final ProducerEntityMapper producerEntityMapper;
	
	public ProducerRepositoryGateway(ProducerRepository producerRepository, ProducerEntityMapper producerEntityMapper) {
		this.producerRepository = producerRepository;
		this.producerEntityMapper = producerEntityMapper;
	}


	@Override
	public Producer create(Producer producerDomain) {
		ProducerEntity producerEntity = producerEntityMapper.toEntityObj(producerDomain);
		ProducerEntity savedObj = producerRepository.save(producerEntity);
		return producerEntityMapper.toDomainObj(savedObj);
	}

	@Override
	public Producer update(Producer producerDomain) {
		ProducerEntity producerEntity = producerEntityMapper.toEntityObjUpd(producerDomain);
		ProducerEntity updatedObj = producerRepository.save(producerEntity);
		return producerEntityMapper.toDomainObj(updatedObj);
	}

	@Override
	public Producer findByName(Producer producerDomain) {
		ProducerEntity result = producerRepository.findByName(producerDomain.getName());
		return producerEntityMapper.toDomainObj(result);
	}
	
	@Override
	public List<Producer> getAll() {
		List<ProducerEntity> result = producerRepository.findAll();
		return producerEntityMapper.toDomainList(result);
	}

	@Override
	public Producer getById(Long id) {
		Optional<ProducerEntity> result = producerRepository.findById(id);
		if(result.isPresent()) {
			return producerEntityMapper.toDomainObj(result.get());
		}
		return null;
	}

	@Override
	public void remove(Long id) {
		producerRepository.deleteById(id);
	}


	@Override
	public boolean isLinkedProducerWithMovie(Long id) {
		int result = producerRepository.isLinkedProducerWithMovie(id);
		return result > 0 ? true : false;
	}


	@Override
	public List<Producer> getWinningProducers() {
		List<ProducerEntity> result = producerRepository.getWinningProducers();
		return producerEntityMapper.toDomainList(result);
	}

}
