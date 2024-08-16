package br.com.outsera.movie.infrastructure.controllers;

public record CreateMovieRequest(String title, Integer year, boolean winner) {

}
