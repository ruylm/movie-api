package br.com.outsera.movie.infrastructure.dto;

import java.util.List;

public record CreateMovieRequest(String title, Integer year, boolean winner, List<CreateStudioRequest> studios, List<CreateProducerRequest> producers) {

}
