package br.com.outsera.movie.infrastructure.dto;

public record GetAwardsResponse(String producer, Integer interval, Integer previousWin, Integer followingWin) {
}
