package br.com.outsera.movie.infrastructure.dto;

import java.util.List;

public record GetIntervalAwardsResponse(List<GetAwardsResponse>	min, List<GetAwardsResponse> max) {
}
