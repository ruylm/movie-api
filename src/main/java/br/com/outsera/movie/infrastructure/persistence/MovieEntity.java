package br.com.outsera.movie.infrastructure.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.outsera.movie.domain.entity.Producer;
import br.com.outsera.movie.domain.entity.Studio;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movie") 
public class MovieEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "year_release", nullable = false)
	private Integer year;
	
	@Column(name = "winner", nullable = false)
	private boolean winner;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "movie_studio",
			joinColumns = @JoinColumn(name = "movie_id"),
			inverseJoinColumns = @JoinColumn(name = "studio_id"))
	private List<StudioEntity> studios;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "movie_producer",
			joinColumns = @JoinColumn(name = "movie_id"),
			inverseJoinColumns = @JoinColumn(name = "producer_id"))
	private List<ProducerEntity> producers;

	
	
	public MovieEntity(String title, Integer year, boolean winner, List<Studio> studios, List<Producer> producers) {
		this.title = title;
		this.year = year;
		this.winner = winner;
		
		if(studios != null) {
			this.studios = new ArrayList<StudioEntity>();
			for(Studio studio : studios) {
				this.studios.add(new StudioEntity(studio.getId(), studio.getName()));
			}
		}
		if(producers != null) {
			this.producers = new ArrayList<ProducerEntity>();
			for(Producer producer : producers) {
				this.producers.add(new ProducerEntity(producer.getId(), producer.getName()));
			}
		}
		
	}
	
	
}
