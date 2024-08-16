package br.com.outsera.movie.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Studio {

	private Long id;
	private String name;
	
	public Studio(String name) {
		this.name = name;
	}
	
	
}
