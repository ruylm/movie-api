package br.com.outsera.movie.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.outsera.movie.application.gateways.MovieGateway;
import br.com.outsera.movie.application.gateways.ProducerGateway;
import br.com.outsera.movie.application.gateways.StudioGateway;
import br.com.outsera.movie.application.usecases.CreateMovieInterector;
import br.com.outsera.movie.application.usecases.CreateProducerInterector;
import br.com.outsera.movie.application.usecases.CreateStudioInterector;
import br.com.outsera.movie.application.usecases.GetIntervalAwardsInterector;
import br.com.outsera.movie.application.usecases.GetMovieByIdInterector;
import br.com.outsera.movie.application.usecases.GetMovieListInterector;
import br.com.outsera.movie.application.usecases.GetProducerByIdInterector;
import br.com.outsera.movie.application.usecases.GetProducerListInterector;
import br.com.outsera.movie.application.usecases.GetStudioByIdInterector;
import br.com.outsera.movie.application.usecases.GetStudioListInterector;
import br.com.outsera.movie.application.usecases.LoadCsvInterector;
import br.com.outsera.movie.application.usecases.RemoveMovieInterector;
import br.com.outsera.movie.application.usecases.RemoveProducerInterector;
import br.com.outsera.movie.application.usecases.RemoveStudioInterector;
import br.com.outsera.movie.application.usecases.UpdateProducerInterector;
import br.com.outsera.movie.application.usecases.UpdateStudioInterector;
import br.com.outsera.movie.infrastructure.dto.IntervalAwardsDTOMapper;
import br.com.outsera.movie.infrastructure.dto.MovieDTOMapper;
import br.com.outsera.movie.infrastructure.dto.ProducerDTOMapper;
import br.com.outsera.movie.infrastructure.dto.StudioDTOMapper;
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
	
	// Movie
	@Bean
	CreateMovieInterector createMovieCase(MovieGateway movieGateway,  StudioGateway studioGateway, ProducerGateway producerGateway) {
		return new CreateMovieInterector(movieGateway, studioGateway, producerGateway);
	}
	
	@Bean
	GetMovieListInterector getMovieListInterector(MovieGateway gateway) {
		return new GetMovieListInterector(gateway);
	}
	
	@Bean
	GetMovieByIdInterector getMovieByIdInterector(MovieGateway gateway) {
		return new GetMovieByIdInterector(gateway);
	}
	
	@Bean
	RemoveMovieInterector removeMovieInterector(MovieGateway gateway) {
		return new RemoveMovieInterector(gateway);
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
	
	// Studio
	@Bean
	CreateStudioInterector createStudioInterector(StudioGateway gateway) {
		return new CreateStudioInterector(gateway);
	}
	
	@Bean
	GetStudioByIdInterector getStudioByIdInterector(StudioGateway gateway) {
		return new GetStudioByIdInterector(gateway);
	}
	
	@Bean
	GetStudioListInterector getStudioListInterector(StudioGateway gateway) {
		return new GetStudioListInterector(gateway);
	}
	
	@Bean
	RemoveStudioInterector removeStudioInterector(StudioGateway gateway) {
		return new RemoveStudioInterector(gateway);
	}
	
	@Bean
	UpdateStudioInterector updateStudioInterector(StudioGateway gateway) {
		return new UpdateStudioInterector(gateway);
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
	StudioDTOMapper studioDTOMapper() {
		return new StudioDTOMapper();
	}
	
	// Producer
	@Bean
	CreateProducerInterector createProducerInterector(ProducerGateway gateway) {
		return new CreateProducerInterector(gateway);
	}
	
	@Bean
	GetProducerByIdInterector getProducerByIdInterector(ProducerGateway gateway) {
		return new GetProducerByIdInterector(gateway);
	}
	
	@Bean
	GetProducerListInterector getProducerListInterector(ProducerGateway gateway) {
		return new GetProducerListInterector(gateway);
	}
	
	@Bean
	RemoveProducerInterector removeProducerInterector(ProducerGateway gateway) {
		return new RemoveProducerInterector(gateway);
	}
	
	@Bean
	UpdateProducerInterector updateProducerInterector(ProducerGateway gateway) {
		return new UpdateProducerInterector(gateway);
	}
	
	@Bean
	ProducerGateway producerGateway(ProducerRepository producerRepository, ProducerEntityMapper producerEntityMapper) {
		return new ProducerRepositoryGateway(producerRepository, producerEntityMapper);
	}
	
	@Bean
	ProducerEntityMapper producerEntityMapper() {
		return new ProducerEntityMapper();
	}
	
	@Bean
	ProducerDTOMapper producerDTOMapper() {
		return new ProducerDTOMapper();
	}
	
	@Bean
	LoadCsvInterector loadCsvInterector(CreateMovieInterector createMovieInterector, MovieDTOMapper movieDTOMappe) {
		return new LoadCsvInterector(createMovieInterector, movieDTOMappe);
	}
	
	@Bean
	GetIntervalAwardsInterector getIntervalAwardsInterector(MovieGateway movieGateway, ProducerGateway producerGateway) {
		return new GetIntervalAwardsInterector(movieGateway, producerGateway);
	}
	
	@Bean
	IntervalAwardsDTOMapper intervalAwardsDTOMapper() {
		return new IntervalAwardsDTOMapper();
	}
}
