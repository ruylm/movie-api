package br.com.outsera.movie.application.gateways;

import br.com.outsera.movie.domain.entity.Producer;

public interface ProducerGateway {
	Producer createProducer(Producer producer);
	Producer findProducerByName(Producer producer);
}
