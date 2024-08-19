package br.com.outsera.movie.application.usecases;

import java.util.ArrayList;
import java.util.List;

import br.com.outsera.movie.application.gateways.MovieGateway;
import br.com.outsera.movie.application.gateways.ProducerGateway;
import br.com.outsera.movie.domain.entity.Awards;
import br.com.outsera.movie.domain.entity.IntervalAwards;
import br.com.outsera.movie.domain.entity.Movie;
import br.com.outsera.movie.domain.entity.Producer;
import br.com.outsera.movie.util.AppConstants;

public class GetIntervalAwardsInterector {
	
	private MovieGateway movieGateway;
	private ProducerGateway producerGateway;
	
	public GetIntervalAwardsInterector(MovieGateway movieGateway, ProducerGateway producerGateway) {
		this.movieGateway = movieGateway;
		this.producerGateway = producerGateway;
	}

	public IntervalAwards getIntervalAwards() {
		
		IntervalAwards intervalAwards = new IntervalAwards();

		List<Producer> producersWin = producerGateway.getWinningProducers();
		
		intervalAwards.setMin(getMinIntervalAwards(producersWin));
		
		intervalAwards.setMax(getMaxIntervalAwards(producersWin));
		
		return intervalAwards;
	}
	
	
	public List<Awards> getMinIntervalAwards(List<Producer> producersWin) {
		
		List<Awards> producerIntervalAwardsList = new ArrayList<Awards>();
		
		for (Producer producer :producersWin) {
			
			Awards lastIntervalAwards = null;
			Awards minIntervalAwards = null;
			List<Movie> movies = movieGateway.getMoviesByProducerId(producer.getId());
			
			if(movies != null) {
				
				for (Movie mv :movies) {
					
					if(lastIntervalAwards == null) {
						
						lastIntervalAwards = new Awards(producer.getId(), producer.getName(), mv.getYear(), mv.getYear());
						continue;
						
					}
					
					Awards currentIntervalAwards = new Awards(producer.getId(), producer.getName(), lastIntervalAwards.getFollowingWin(), mv.getYear());
					
					if(minIntervalAwards == null) {
						minIntervalAwards = currentIntervalAwards.getClone();
						lastIntervalAwards = currentIntervalAwards.getClone();
						continue;
					}
					
					if(currentIntervalAwards.getInterval() < minIntervalAwards.getInterval()) {
						minIntervalAwards = currentIntervalAwards.getClone();
					}
					
					lastIntervalAwards = currentIntervalAwards.getClone();
					
				}
				
			}

			if(minIntervalAwards != null) {
				producerIntervalAwardsList.add(minIntervalAwards);
			}
			
		}
		
		return filterValuesByType(producerIntervalAwardsList, AppConstants.MIN_VALUE);
		
	}
	
	public List<Awards> getMaxIntervalAwards(List<Producer> producersWin) {
		
		List<Awards> producerIntervalAwardsList = new ArrayList<Awards>();
		
		for (Producer producer :producersWin) {
			
			Awards lastIntervalAwards = null;
			Awards maxIntervalAwards = null;
			List<Movie> movies = movieGateway.getMoviesByProducerId(producer.getId());
			
			if(movies != null) {
				
				for (Movie mv :movies) {
					
					if(lastIntervalAwards == null) {
						
						lastIntervalAwards = new Awards(producer.getId(), producer.getName(), mv.getYear(), mv.getYear());
						continue;
						
					}
					
					Awards currentIntervalAwards = new Awards(producer.getId(), producer.getName(), lastIntervalAwards.getFollowingWin(), mv.getYear());
					
					if(maxIntervalAwards == null) {
						maxIntervalAwards = currentIntervalAwards.getClone();
						lastIntervalAwards = currentIntervalAwards.getClone();
						continue;
					}
					
					if(currentIntervalAwards.getInterval() > maxIntervalAwards.getInterval()) {
						maxIntervalAwards = currentIntervalAwards.getClone();
					}
					
					lastIntervalAwards = currentIntervalAwards.getClone();
					
				}
				
			}

			if(maxIntervalAwards != null) {
				producerIntervalAwardsList.add(maxIntervalAwards);
			}
			
		}
		
		return filterValuesByType(producerIntervalAwardsList, AppConstants.MAX_VALUE);
	}
	
	public List<Awards> filterValuesByType (List<Awards> IntervalAwardsList, String type) {
		
		List<Awards> intervalAward = new ArrayList<Awards>();
		
		IntervalAwardsList.stream().forEach((item) -> {
		    
		    if(intervalAward.isEmpty()) {
				intervalAward.add(item);
			}
		    else if(item.getInterval() == intervalAward.get(0).getInterval()) {
		    	intervalAward.add(item);
		    }
		    else if(type == AppConstants.MIN_VALUE && item.getInterval() < intervalAward.get(0).getInterval()) {
		    	intervalAward.clear();
		    	intervalAward.add(item);
		    }
		    else if(type == AppConstants.MAX_VALUE && item.getInterval() > intervalAward.get(0).getInterval()) {
		    	intervalAward.clear();
		    	intervalAward.add(item);
		    }
		    
		});
		
		return intervalAward;
		
	}
}
