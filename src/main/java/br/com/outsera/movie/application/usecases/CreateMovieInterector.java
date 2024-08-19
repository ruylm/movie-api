package br.com.outsera.movie.application.usecases;

import java.util.ArrayList;
import java.util.List;

import br.com.outsera.movie.application.gateways.MovieGateway;
import br.com.outsera.movie.application.gateways.ProducerGateway;
import br.com.outsera.movie.application.gateways.StudioGateway;
import br.com.outsera.movie.domain.entity.Movie;
import br.com.outsera.movie.domain.entity.Producer;
import br.com.outsera.movie.domain.entity.Studio;

public class CreateMovieInterector {
	
	private MovieGateway movieGateway;
	private StudioGateway studioGateway;
	private ProducerGateway producerGateway;
	
	public CreateMovieInterector(MovieGateway movieGateway, StudioGateway studioGateway, ProducerGateway producerGateway) {
		this.movieGateway = movieGateway;
		this.studioGateway = studioGateway;
		this.producerGateway = producerGateway;
	}

	public Movie createMovie(Movie movie) {
		
		List<Studio> studios = new ArrayList<Studio>();
		for(Studio s : movie.getStudios()) {
			Studio studio = studioGateway.findByName(s);
			if(studio == null) {
				studio = new Studio(s.getName());
				studio = studioGateway.create(studio);
			}
			studios.add(studio);
		}
		movie.setStudios(studios);
		
		List<Producer> producers = new ArrayList<Producer>();
		for(Producer s : movie.getProducers()) {
			Producer producer = producerGateway.findByName(s);
			if(producer == null) {
				producer = new Producer(s.getName());
				producer = producerGateway.create(producer);
			}
			producers.add(producer);
		}
		movie.setProducers(producers);
		
		return movieGateway.createMovie(movie);
	}

}
