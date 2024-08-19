package br.com.outsera.movie.infrastructure.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import br.com.outsera.movie.infrastructure.dto.CreateMovieRequest;
import br.com.outsera.movie.infrastructure.dto.CreateProducerRequest;
import br.com.outsera.movie.infrastructure.dto.CreateStudioRequest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {
	
	@LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void addMovie() {
		
		List<CreateStudioRequest> studios = new ArrayList<CreateStudioRequest>();
		studios.add(new CreateStudioRequest("Studio0123456"));
		List<CreateProducerRequest> producers = new ArrayList<CreateProducerRequest>();
		producers.add(new CreateProducerRequest("Producer0123456"));
		
		CreateMovieRequest request = new CreateMovieRequest("Title0123456", 1987, true, studios, producers);

		HttpEntity<CreateMovieRequest> entity = new HttpEntity<CreateMovieRequest>(request, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/movies"),
				HttpMethod.POST, entity, String.class);

		HttpStatusCode actual = response.getStatusCode();

		assertTrue(actual.value() == 201);

	}
	
	private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
	
}
