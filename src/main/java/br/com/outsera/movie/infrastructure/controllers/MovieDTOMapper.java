package br.com.outsera.movie.infrastructure.controllers;

import java.util.ArrayList;
import java.util.List;

import br.com.outsera.movie.domain.entity.Movie;
import br.com.outsera.movie.domain.entity.Producer;
import br.com.outsera.movie.domain.entity.Studio;

public class MovieDTOMapper {
	
	public CreateMovieResponse toResponse(Movie movie) {
		return new CreateMovieResponse(movie.getTitle(), movie.getYear(), movie.isWinner());
	}
	
	public Movie toMovie(CreateMovieRequest request) {
		return new Movie(null, request.title(), request.year(), request.winner(), null, null);
	}
	
	public Movie toMovie(String[] line) {
		return new Movie(line);
	}
	
	public GetMovieResponse toGetMovieResponse(Movie movie) {
		return new GetMovieResponse(movie.getId(), movie.getTitle(), movie.getYear(), movie.isWinner(), toStudioResponseList(movie.getStudios()), toProducerResponseList(movie.getProducers()));
	}
	
	public List<GetMovieResponse> toResponseList(List<Movie> movieList) {
		List<GetMovieResponse> createMovieResponseList = new ArrayList<GetMovieResponse>();
		for(Movie movie: movieList) {
			createMovieResponseList.add(toGetMovieResponse(movie));
		}
		return createMovieResponseList;
	}
	
	public GetStudioResponse toStudioResponse(Studio std) {
		return new GetStudioResponse(std.getId(), std.getName());
	}
	
	public List<GetStudioResponse> toStudioResponseList(List<Studio> list) {
		List<GetStudioResponse> responseList = new ArrayList<GetStudioResponse>();
		for(Studio std: list) {
			responseList.add(toStudioResponse(std));
		}
		return responseList;
	}
	
	public GetProducerResponse toProducerResponse(Producer std) {
		return new GetProducerResponse(std.getId(), std.getName());
	}
	
	public List<GetProducerResponse> toProducerResponseList(List<Producer> list) {
		List<GetProducerResponse> responseList = new ArrayList<GetProducerResponse>();
		for(Producer std: list) {
			responseList.add(toProducerResponse(std));
		}
		return responseList;
	}
	
}
