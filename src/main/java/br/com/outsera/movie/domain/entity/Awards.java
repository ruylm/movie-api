package br.com.outsera.movie.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Awards implements Cloneable {

	private Long idProducer;
	private String producer;
	private Integer interval;
	private Integer previousWin;
	private Integer followingWin;
	
	public Awards(Long idProducer, String producer, Integer previousWin, Integer followingWin) {
		this.idProducer = idProducer;
		this.producer = producer;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
		
		this.interval = followingWin - previousWin; 
	}
	
	public Awards getClone() {
        try {
            return (Awards) super.clone();
        } catch (CloneNotSupportedException e) {
            return this;
        }
    }
	
	
}
