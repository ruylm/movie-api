package br.com.outsera.movie.application.usecases;

import java.util.List;

import br.com.outsera.movie.application.gateways.ProducerGateway;
import br.com.outsera.movie.domain.entity.Producer;

public class GetProducerListInterector {
	
	private ProducerGateway producerGateway;
	
	public GetProducerListInterector(ProducerGateway producerGateway) {
		this.producerGateway = producerGateway;
	}


	public List<Producer> getProducerList() {
		return producerGateway.getAll();
	}
	
}
