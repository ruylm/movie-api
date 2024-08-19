package br.com.outsera.movie.application.usecases;

import br.com.outsera.movie.application.gateways.StudioGateway;
import br.com.outsera.movie.domain.entity.Studio;

public class CreateStudioInterector {
	
	private StudioGateway studioGateway;
	
	public CreateStudioInterector(StudioGateway studioGateway) {
		this.studioGateway = studioGateway;
	}

	public Studio create(Studio s) {
		
		Studio studio = studioGateway.findByName(s);
		
		if(studio == null) {
			studio = new Studio(s.getName());
			studio = studioGateway.create(studio);
		}
		
		return studio;
	}

}
