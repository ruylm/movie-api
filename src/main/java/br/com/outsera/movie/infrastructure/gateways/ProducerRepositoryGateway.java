package br.com.outsera.movie.infrastructure.gateways;

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
	public Producer createProducer(Producer producerDomain) {
		ProducerEntity producerEntity = producerEntityMapper.toEntityObj(producerDomain);
		ProducerEntity savedObj = producerRepository.save(producerEntity);
		return producerEntityMapper.toDomainObj(savedObj);
	}


	@Override
	public Producer findProducerByName(Producer producerDomain) {
		ProducerEntity result = producerRepository.findByName(producerDomain.getName());
		return producerEntityMapper.toDomainObj(result);
	}

}
