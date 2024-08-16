package br.com.outsera.movie.domain.entity;

import java.util.ArrayList;
import java.util.List;

import br.com.outsera.movie.infrastructure.persistence.ProducerEntity;
import br.com.outsera.movie.infrastructure.persistence.StudioEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Movie {
	
	private Long id;
	
	private String title;
	
	private Integer year;
	
	private boolean winner;
	
	private List<Studio> studios;
	
	private List<Producer> producers;

	public Movie(String[] line) {
		super();
		this.year = line[0] != null ? Integer.parseInt(line[0].trim()) : null;
		this.title = line[1] != null ? line[1].trim() : null;
		this.winner = line[4] != null && line[4].trim().equalsIgnoreCase("yes") ? true : false;
		
		if(line[2] != null) {
			this.studios = new ArrayList<Studio>();
			String[] studiosList = line[2].split(",");
			for(String studio : studiosList) {
				this.studios.add(new Studio(studio.trim()));
			}
		}
		
		if(line[3] != null) {
			this.producers = new ArrayList<Producer>();
			String[] producersList = line[3].split(",| and "); 
			for(String producer : producersList) {
				this.producers.add(new Producer(producer.trim()));
			}
		}
		
	}


	public Movie(Long id, String title, Integer year, boolean winner, List<StudioEntity> studios, List<ProducerEntity> producers) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.winner = winner;
		
		if(studios != null) {
			this.studios = new ArrayList<Studio>();
			for(StudioEntity studio : studios) {
				this.studios.add(new Studio(studio.getId(), studio.getName()));
			}
		}
		if(producers != null) {
			this.producers = new ArrayList<Producer>();
			for(ProducerEntity producer : producers) {
				this.producers.add(new Producer(producer.getId(), producer.getName()));
			}
		}
	}
	
	

}
