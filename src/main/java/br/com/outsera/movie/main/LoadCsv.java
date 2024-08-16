package br.com.outsera.movie.main;

import java.io.File;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import br.com.outsera.movie.application.usecases.CreateMovieInterector;
import br.com.outsera.movie.domain.entity.Movie;
import br.com.outsera.movie.infrastructure.controllers.MovieDTOMapper;
import jakarta.annotation.PostConstruct;

@Component
public class LoadCsv {
	
	private static final Logger logger = LoggerFactory.getLogger(LoadCsv.class);
	
	private final MovieDTOMapper movieDTOMapper;
	private final CreateMovieInterector createMovieInterector; // service

	public LoadCsv(CreateMovieInterector createMovieInterector, MovieDTOMapper movieDTOMappe) {
		this.createMovieInterector = createMovieInterector;
		this.movieDTOMapper = movieDTOMappe;
	}
	
	@PostConstruct
	public void loadMovies() {

		logger.info("Starting data load...");
		
		readAllFilesFromResourceFolder();
		
	}
	
	
	public void readAllFilesFromResourceFolder() { 
		  
	    try {
	    	
	    	File folder = ResourceUtils.getFile("classpath:movies/");
	        
	        for (final File fileEntry : folder.listFiles()) {
	        	
	        	if (fileEntry.isDirectory()) {
	                continue;
	            }
	            readDataLineByLine(fileEntry.getPath());
	        }
	    } 
	    catch (Exception e) { 
	    	logger.error(e.getMessage());
	    } 
	} 
	
	public void readDataLineByLine(String file) { 
		  
	    try {
	    	
	    	CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build(); // custom separator
	  
	        FileReader filereader = new FileReader(file); 
	  
	        try (CSVReader csvReader = new CSVReaderBuilder(filereader)
	        		.withCSVParser(csvParser)
	    	        .withSkipLines(1)
    	          	.build()) {
				
	        	String[] nextRecord; 
  
				// read data line by line 
				while ((nextRecord = csvReader.readNext()) != null) { 
					
					Movie movieBusinessObj = movieDTOMapper.toMovie(nextRecord);
					
					createMovieInterector.createMovie(movieBusinessObj);
					
					logger.info(nextRecord[0] + " - " + nextRecord[1] + " - " + nextRecord[2] + " - " + nextRecord[3] + " - " + nextRecord[4]);
				}
			} 
	    } 
	    catch (Exception e) { 
	        logger.error(e.getMessage());
	    } 
	}
	

}
