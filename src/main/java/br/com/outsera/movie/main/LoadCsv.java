package br.com.outsera.movie.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import br.com.outsera.movie.application.usecases.LoadCsvInterector;

@Component
public class LoadCsv implements InitializingBean {
	
	private static final Logger logger = LoggerFactory.getLogger(LoadCsv.class);
	
	private final LoadCsvInterector loadCsvInterector;

	public LoadCsv(LoadCsvInterector loadCsvInterector) {
		this.loadCsvInterector = loadCsvInterector;
	}
	
//	@PostConstruct
//	public void loadMovies() {
//
//		logger.info("Starting data load...");
//		
//		loadCsvInterector.readAllFilesFromResourceFolder();
//		
//	}
	

	@Override
	public void afterPropertiesSet() throws Exception {
		
		logger.info("Starting data load...");
		
		loadCsvInterector.readAllFilesFromResourceFolder();
		
	}

}
