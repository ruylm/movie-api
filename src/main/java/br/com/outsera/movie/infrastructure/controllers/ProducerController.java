package br.com.outsera.movie.infrastructure.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.outsera.movie.application.usecases.CreateProducerInterector;
import br.com.outsera.movie.application.usecases.GetProducerByIdInterector;
import br.com.outsera.movie.application.usecases.GetProducerListInterector;
import br.com.outsera.movie.application.usecases.RemoveProducerInterector;
import br.com.outsera.movie.application.usecases.UpdateProducerInterector;
import br.com.outsera.movie.domain.entity.Producer;
import br.com.outsera.movie.infrastructure.dto.CreateProducerRequest;
import br.com.outsera.movie.infrastructure.dto.GetProducerResponse;
import br.com.outsera.movie.infrastructure.dto.ProducerDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Producer", description = "Proucer management APIs")
@RestController
@RequestMapping("/producers")
public class ProducerController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);
	
	private final CreateProducerInterector createProducerInterector;
	private final UpdateProducerInterector updateProducerInterector;
	private final GetProducerListInterector getProducerListInterector;
	private final GetProducerByIdInterector getProducerByIdInterector;
	private final RemoveProducerInterector removeProducerInterector;
	private final ProducerDTOMapper producerDTOMapper;

	public ProducerController(CreateProducerInterector createProducerInterector, GetProducerListInterector getProducerListInterector, UpdateProducerInterector updateProducerInterector,
			GetProducerByIdInterector getProducerByIdInterector, RemoveProducerInterector removeProducerInterector, ProducerDTOMapper producerDTOMapper) {
		this.createProducerInterector = createProducerInterector;
		this.updateProducerInterector = updateProducerInterector;
		this.getProducerListInterector = getProducerListInterector;
		this.getProducerByIdInterector = getProducerByIdInterector;
		this.removeProducerInterector = removeProducerInterector;
		this.producerDTOMapper = producerDTOMapper;
	}
	
	
	@Operation(summary = "Create a producer")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "201", description = "Producer created", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = GetProducerResponse.class)) })})
	@PostMapping
	public ResponseEntity<GetProducerResponse> create(@RequestBody CreateProducerRequest request) {
		Producer producerBusinessObj = producerDTOMapper.toProducer(request);
		Producer producer = createProducerInterector.create(producerBusinessObj);
		return ResponseEntity.status(HttpStatus.CREATED).body(producerDTOMapper.toResponse(producer));
	}
	
	@Operation(summary = "Retrieves a list of producers")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = GetProducerResponse.class)) })})
	@GetMapping
	public ResponseEntity<List<GetProducerResponse>> getAll() {
		List<Producer> producerList = getProducerListInterector.getProducerList();
		return ResponseEntity.status(HttpStatus.OK).body(producerDTOMapper.toResponseList(producerList));
	}
	
	@Operation(summary = "Retrieve a producer by Id")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = GetProducerResponse.class)) }),
		  @ApiResponse(responseCode = "404", description = "Producer not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<GetProducerResponse> getById(@PathVariable("id") Long id) {
		Producer producer = getProducerByIdInterector.getProducerById(id);
		if(producer == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} 
		return ResponseEntity.status(HttpStatus.OK).body(producerDTOMapper.toResponse(producer));
	}

	@Operation(summary = "Delete a producer by Id")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "204", content = @Content),
		  @ApiResponse(responseCode = "404", description = "Producer not found", content = @Content), 
		  @ApiResponse(responseCode = "409", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable("id") Long id) {
		if(getProducerByIdInterector.getProducerById(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		if(getProducerByIdInterector.isLinkedProducerWithMovie(id)) {
			logger.error("Producer is linked with a movie.");
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
		removeProducerInterector.removeProducer(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Update a producer")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "204", content = @Content),
			  @ApiResponse(responseCode = "404", description = "Producer not found", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody CreateProducerRequest request) {
		Producer producer = getProducerByIdInterector.getProducerById(id);
		if(producer == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} 
		//Producer producerBusinessObj = producerDTOMapper.toProducer(request);
		producer.setName(request.name());
		updateProducerInterector.update(producer);
		return ResponseEntity.noContent().build();
	}
	
}
