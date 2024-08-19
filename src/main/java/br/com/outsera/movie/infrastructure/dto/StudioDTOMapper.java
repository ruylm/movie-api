package br.com.outsera.movie.infrastructure.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.outsera.movie.domain.entity.Studio;

public class StudioDTOMapper {
	
	public Studio toStudio(CreateStudioRequest request) {
		return new Studio(request.name());
	}
	
	public GetStudioResponse toResponse(Studio std) {
		return new GetStudioResponse(std.getId(), std.getName());
	}
	
	public List<GetStudioResponse> toResponseList(List<Studio> studioList) {
		List<GetStudioResponse> responseList = new ArrayList<GetStudioResponse>();
		for(Studio studio: studioList) {
			responseList.add(toResponse(studio));
		}
		return responseList;
	}
	
}
