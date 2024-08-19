package br.com.outsera.movie.application.usecases;

import br.com.outsera.movie.application.gateways.StudioGateway;
import br.com.outsera.movie.domain.entity.Studio;

public class GetStudioByIdInterector {
	
	private StudioGateway studioGateway;
	
	public GetStudioByIdInterector(StudioGateway studioGateway) {
		this.studioGateway = studioGateway;
	}

	public Studio getStudioById(Long id) {
		return studioGateway.getById(id);
	}
	
	public boolean isLinkedStudioWithMovie(Long id) {
		return studioGateway.isLinkedStudioWithMovie(id);
	}
	
}
