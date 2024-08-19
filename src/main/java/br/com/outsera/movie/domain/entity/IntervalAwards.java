package br.com.outsera.movie.domain.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntervalAwards {

	private List<Awards> min;
	private List<Awards> max;
	
}
