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

import br.com.outsera.movie.application.usecases.CreateStudioInterector;
import br.com.outsera.movie.application.usecases.GetStudioByIdInterector;
import br.com.outsera.movie.application.usecases.GetStudioListInterector;
import br.com.outsera.movie.application.usecases.RemoveStudioInterector;
import br.com.outsera.movie.application.usecases.UpdateStudioInterector;
import br.com.outsera.movie.domain.entity.Studio;
import br.com.outsera.movie.infrastructure.dto.CreateStudioRequest;
import br.com.outsera.movie.infrastructure.dto.GetStudioResponse;
import br.com.outsera.movie.infrastructure.dto.StudioDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Studio", description = "Studio management APIs")
@RestController
@RequestMapping("/studios")
public class StudioController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudioController.class);
	
	private final CreateStudioInterector createStudioInterector;
	private final UpdateStudioInterector updateStudioInterector;
	private final GetStudioListInterector getStudioListInterector;
	private final GetStudioByIdInterector getStudioByIdInterector;
	private final RemoveStudioInterector removeStudioInterector;
	private final StudioDTOMapper studioDTOMapper;

	public StudioController(CreateStudioInterector createStudioInterector, GetStudioListInterector getStudioListInterector, UpdateStudioInterector updateStudioInterector,
			GetStudioByIdInterector getStudioByIdInterector, RemoveStudioInterector removeStudioInterector, StudioDTOMapper studioDTOMapper) {
		this.createStudioInterector = createStudioInterector;
		this.updateStudioInterector = updateStudioInterector;
		this.getStudioListInterector = getStudioListInterector;
		this.getStudioByIdInterector = getStudioByIdInterector;
		this.removeStudioInterector = removeStudioInterector;
		this.studioDTOMapper = studioDTOMapper;
	}
	
	
	@Operation(summary = "Create a studio")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "201", description = "Studio created", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = GetStudioResponse.class)) })})
	@PostMapping
	public ResponseEntity<GetStudioResponse> create(@RequestBody CreateStudioRequest request) {
		Studio studioBusinessObj = studioDTOMapper.toStudio(request);
		Studio studio = createStudioInterector.create(studioBusinessObj);
		return ResponseEntity.status(HttpStatus.CREATED).body(studioDTOMapper.toResponse(studio));
	}
	
	@Operation(summary = "Retrieves a list of studios")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = GetStudioResponse.class)) })})
	@GetMapping
	public ResponseEntity<List<GetStudioResponse>> getAll() {
		List<Studio> studioList = getStudioListInterector.getMovieList();
		return ResponseEntity.status(HttpStatus.OK).body(studioDTOMapper.toResponseList(studioList));
	}
	
	@Operation(summary = "Retrieve a studio by Id")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = GetStudioResponse.class)) }),
		  @ApiResponse(responseCode = "404", description = "Studio not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<GetStudioResponse> getById(@PathVariable("id") Long id) {
		Studio studio = getStudioByIdInterector.getStudioById(id);
		if(studio == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} 
		return ResponseEntity.status(HttpStatus.OK).body(studioDTOMapper.toResponse(studio));
	}

	@Operation(summary = "Delete a studio by Id")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "204", content = @Content),
		  @ApiResponse(responseCode = "404", description = "Studio not found", content = @Content), 
		  @ApiResponse(responseCode = "409", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable("id") Long id) {
		if(getStudioByIdInterector.getStudioById(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		if(getStudioByIdInterector.isLinkedStudioWithMovie(id)) {
			logger.error("Studio is linked with a movie.");
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
		removeStudioInterector.removeStudio(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Update a studio")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "204", content = @Content),
			  @ApiResponse(responseCode = "404", description = "Studio not found", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody CreateStudioRequest request) {
		Studio studio = getStudioByIdInterector.getStudioById(id);
		if(studio == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		//Studio studioBusinessObj = studioDTOMapper.toStudio(request);
		studio.setName(request.name());
		updateStudioInterector.update(studio);
		return ResponseEntity.noContent().build();
	}
	
}
