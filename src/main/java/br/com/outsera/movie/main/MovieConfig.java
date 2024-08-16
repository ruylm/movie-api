package br.com.outsera.movie.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.outsera.movie.application.gateways.MovieGateway;
import br.com.outsera.movie.application.gateways.ProducerGateway;
import br.com.outsera.movie.application.gateways.StudioGateway;
import br.com.outsera.movie.application.usecases.CreateMovieInterector;
import br.com.outsera.movie.application.usecases.GetMovieByIdInterector;
import br.com.outsera.movie.application.usecases.GetMovieListInterector;
import br.com.outsera.movie.application.usecases.RemoveMovieInterector;
import br.com.outsera.movie.infrastructure.controllers.MovieDTOMapper;
import br.com.outsera.movie.infrastructure.gateways.MovieEntityMapper;
import br.com.outsera.movie.infrastructure.gateways.MovieRepositoryGateway;
import br.com.outsera.movie.infrastructure.gateways.ProducerEntityMapper;
import br.com.outsera.movie.infrastructure.gateways.ProducerRepositoryGateway;
import br.com.outsera.movie.infrastructure.gateways.StudioEntityMapper;
import br.com.outsera.movie.infrastructure.gateways.StudioRepositoryGateway;
import br.com.outsera.movie.infrastructure.persistence.MovieRepository;
import br.com.outsera.movie.infrastructure.persistence.ProducerRepository;
import br.com.outsera.movie.infrastructure.persistence.StudioRepository;

@Configuration
public class MovieConfig {
	
	@Bean
	CreateMovieInterector createMovieCase(MovieGateway movieGateway,  StudioGateway studioGateway, ProducerGateway producerGateway) {
		return new CreateMovieInterector(movieGateway, studioGateway, producerGateway);
	}
	
	@Bean
	GetMovieListInterector getMovieListInterector(MovieGateway movieGateway) {
		return new GetMovieListInterector(movieGateway);
	}
	
	@Bean
	GetMovieByIdInterector getMovieByIdInterector(MovieGateway movieGateway) {
		return new GetMovieByIdInterector(movieGateway);
	}
	
	@Bean
	RemoveMovieInterector removeMovieInterector(MovieGateway movieGateway) {
		return new RemoveMovieInterector(movieGateway);
	}
	
	@Bean
	MovieGateway movieGateway(MovieRepository movieRepository, MovieEntityMapper movieEntityMapper) {
		return new MovieRepositoryGateway(movieRepository, movieEntityMapper);
	}
	
	@Bean
	MovieEntityMapper movieEntityMapper() {
		return new MovieEntityMapper();
	}
	
	@Bean
	MovieDTOMapper movieDTOMapper() {
		return new MovieDTOMapper();
	}
	
	
	@Bean
	StudioGateway studioGateway(StudioRepository studioRepository, StudioEntityMapper studioEntityMapper) {
		return new StudioRepositoryGateway(studioRepository, studioEntityMapper);
	}
	
	@Bean
	StudioEntityMapper studioEntityMapper() {
		return new StudioEntityMapper();
	}

	@Bean
	ProducerGateway producerGateway(ProducerRepository producerRepository, ProducerEntityMapper producerEntityMapper) {
		return new ProducerRepositoryGateway(producerRepository, producerEntityMapper);
	}
	
	@Bean
	ProducerEntityMapper producerEntityMapper() {
		return new ProducerEntityMapper();
	}
}
