package br.com.outsera.movie.infrastructure.dto;

import java.util.List;

public record GetMovieResponse(Long id, String title, Integer year, boolean winner, List<GetStudioResponse> studios, List<GetProducerResponse> producers) {

}
