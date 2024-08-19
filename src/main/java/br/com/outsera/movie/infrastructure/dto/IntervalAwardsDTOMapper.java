package br.com.outsera.movie.infrastructure.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.outsera.movie.domain.entity.Awards;
import br.com.outsera.movie.domain.entity.IntervalAwards;

public class IntervalAwardsDTOMapper {
	
	public GetAwardsResponse toGetAwardsResponse(Awards awards) {
		return new GetAwardsResponse(awards.getProducer(), awards.getInterval(), awards.getPreviousWin(), awards.getFollowingWin());
	}
	
	public GetIntervalAwardsResponse toGetIntervalAwardsResponse(IntervalAwards intervalAwards) {
		return new GetIntervalAwardsResponse(toResponseList(intervalAwards.getMin()), toResponseList(intervalAwards.getMax()));
	}
	
	public List<GetAwardsResponse> toResponseList(List<Awards> awards) {
		List<GetAwardsResponse> responseList = new ArrayList<GetAwardsResponse>();
		for(Awards item: awards) {
			responseList.add(toGetAwardsResponse(item));
		}
		return responseList;
	}
	
}
