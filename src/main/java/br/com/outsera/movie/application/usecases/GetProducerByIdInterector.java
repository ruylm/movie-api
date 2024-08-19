package br.com.outsera.movie.application.usecases;

import br.com.outsera.movie.application.gateways.ProducerGateway;
import br.com.outsera.movie.domain.entity.Producer;

public class GetProducerByIdInterector {
	
	private ProducerGateway producerGateway;
	
	public GetProducerByIdInterector(ProducerGateway producerGateway) {
		this.producerGateway = producerGateway;
	}

	public Producer getProducerById(Long id) {
		return producerGateway.getById(id);
	}
	
	public boolean isLinkedProducerWithMovie(Long id) {
		return producerGateway.isLinkedProducerWithMovie(id);
	}
	
}
