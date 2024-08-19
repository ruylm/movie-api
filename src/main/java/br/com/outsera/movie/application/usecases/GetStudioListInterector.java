package br.com.outsera.movie.application.usecases;

import java.util.List;

import br.com.outsera.movie.application.gateways.StudioGateway;
import br.com.outsera.movie.domain.entity.Studio;

public class GetStudioListInterector {
	
	private StudioGateway studioGateway;
	
	public GetStudioListInterector(StudioGateway studioGateway) {
		this.studioGateway = studioGateway;
	}


	public List<Studio> getMovieList() {
		return studioGateway.getAll();
	}
	
}
