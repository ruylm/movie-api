package br.com.outsera.movie.application.usecases;

import br.com.outsera.movie.application.gateways.ProducerGateway;

public class RemoveProducerInterector {
	
	private ProducerGateway producerGateway;
	
	public RemoveProducerInterector(ProducerGateway producerGateway) {
		this.producerGateway = producerGateway;
	}

	public void removeProducer(Long id) {
		producerGateway.remove(id);
	}
	
}
