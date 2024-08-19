package br.com.outsera.movie.application.gateways;

import java.util.List;

import br.com.outsera.movie.domain.entity.Producer;

public interface ProducerGateway {
	public Producer create(Producer producer);
	public Producer update(Producer producer);
	public Producer findByName(Producer producer);
	public List<Producer> getAll();
	public Producer getById(Long id);
	public void remove(Long id);
	public boolean isLinkedProducerWithMovie(Long id);
	public List<Producer> getWinningProducers();
}
