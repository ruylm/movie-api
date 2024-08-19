package br.com.outsera.movie.infrastructure.dto;

import java.util.List;

import br.com.outsera.movie.domain.entity.Producer;
import br.com.outsera.movie.domain.entity.Studio;

public record CreateMovieResponse(Long id, String title, Integer year, boolean winner, List<Studio> studios, List<Producer> producers) {

}
