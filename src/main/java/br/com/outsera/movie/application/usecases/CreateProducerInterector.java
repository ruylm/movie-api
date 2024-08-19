package br.com.outsera.movie.application.usecases;

import br.com.outsera.movie.application.gateways.ProducerGateway;
import br.com.outsera.movie.domain.entity.Producer;

public class CreateProducerInterector {
	
	private ProducerGateway producerGateway;
	
	public CreateProducerInterector(ProducerGateway producerGateway) {
		this.producerGateway = producerGateway;
	}

	public Producer create(Producer p) {
		
		Producer producer = producerGateway.findByName(p);
		
		if(producer == null) {
			producer = new Producer(p.getName());
			producer = producerGateway.create(producer);
		}
		
		return producer;
	}

}
