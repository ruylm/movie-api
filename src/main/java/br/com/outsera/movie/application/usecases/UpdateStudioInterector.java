package br.com.outsera.movie.application.usecases;

import br.com.outsera.movie.application.gateways.StudioGateway;
import br.com.outsera.movie.domain.entity.Studio;

public class UpdateStudioInterector {
	
	private StudioGateway studioGateway;
	
	public UpdateStudioInterector(StudioGateway studioGateway) {
		this.studioGateway = studioGateway;
	}

	public void update(Studio studio) {
		
		studioGateway.update(studio);
		
	}

}
