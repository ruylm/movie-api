package br.com.outsera.movie.infrastructure.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.outsera.movie.domain.entity.Producer;

public class ProducerDTOMapper {
	
	public Producer toProducer(CreateProducerRequest request) {
		return new Producer(request.name());
	}
	
	public GetProducerResponse toResponse(Producer std) {
		return new GetProducerResponse(std.getId(), std.getName());
	}
	
	public List<GetProducerResponse> toResponseList(List<Producer> producerList) {
		List<GetProducerResponse> responseList = new ArrayList<GetProducerResponse>();
		for(Producer producer: producerList) {
			responseList.add(toResponse(producer));
		}
		return responseList;
	}
	
}
