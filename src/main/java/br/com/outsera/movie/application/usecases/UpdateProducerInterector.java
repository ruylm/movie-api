package br.com.outsera.movie.application.usecases;

import br.com.outsera.movie.application.gateways.ProducerGateway;
import br.com.outsera.movie.domain.entity.Producer;

public class UpdateProducerInterector {
	
	private ProducerGateway producerGateway;
	
	public UpdateProducerInterector(ProducerGateway producerGateway) {
		this.producerGateway = producerGateway;
	}

	public void update(Producer producer) {
		
		producerGateway.update(producer);
		
	}

}
