package br.com.outsera.movie.application.usecases;

import br.com.outsera.movie.application.gateways.StudioGateway;

public class RemoveStudioInterector {
	
	private StudioGateway studioGateway;
	
	public RemoveStudioInterector(StudioGateway studioGateway) {
		this.studioGateway = studioGateway;
	}

	public void removeStudio(Long id) {
		studioGateway.remove(id);
	}
	
}
