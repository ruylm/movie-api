package br.com.outsera.movie.application.usecases;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import br.com.outsera.movie.domain.entity.Movie;
import br.com.outsera.movie.infrastructure.dto.MovieDTOMapper;
import br.com.outsera.movie.util.AppConstants;

@Component
public class LoadCsvInterector {
	
	private static final Logger logger = LoggerFactory.getLogger(LoadCsvInterector.class);
	
	private final MovieDTOMapper movieDTOMapper;
	private final CreateMovieInterector createMovieInterector;

	public LoadCsvInterector(CreateMovieInterector createMovieInterector, MovieDTOMapper movieDTOMappe) {
		this.createMovieInterector = createMovieInterector;
		this.movieDTOMapper = movieDTOMappe;
	}
	
	
	public void readAllFilesFromResourceFolder() { 
		  
	    try {
	    	File folder = ResourceUtils.getFile(AppConstants.PATH_CSV);
	        
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
					
					//logger.info(nextRecord[0] + " - " + nextRecord[1] + " - " + nextRecord[2] + " - " + nextRecord[3] + " - " + nextRecord[4]);
				}
			} 
	    } 
	    catch (Exception e) { 
	        logger.error(e.getMessage());
	    } 
	}
	
	public List<Movie> readLimitedFilesFromResourceFolder(int limit) { 
		  
	    try {
	    	
	    	File folder = ResourceUtils.getFile(AppConstants.PATH_CSV);
	        
	        for (final File fileEntry : folder.listFiles()) {
	        	
	        	if (fileEntry.isDirectory()) {
	                continue;
	            }
	        	return readDataLineByLineLimited(fileEntry.getPath(), limit);
	        }
	    } 
	    catch (Exception e) { 
	    	logger.error(e.getMessage());
	    }
		return null; 
	}
	
	
	public List<Movie> readDataLineByLineLimited(String file, int limit) {
		int count = 0;
		List<Movie> movieList = new ArrayList<Movie>();
	    try {
	    	
	    	CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build(); // custom separator
	  
	        FileReader filereader = new FileReader(file);
	  
	        try (CSVReader csvReader = new CSVReaderBuilder(filereader)
	        		.withCSVParser(csvParser)
	    	        .withSkipLines(1)
    	          	.build()) {
				
	        	String[] nextRecord; 
  
				// read data line by line 
				while ((nextRecord = csvReader.readNext()) != null && count < limit) { 
					
					movieList.add(movieDTOMapper.toMovie(nextRecord));
					count++;
					
				}
			} 
	    } 
	    catch (Exception e) { 
	        logger.error(e.getMessage());
	    }
	    return movieList;
	}
	
	public Integer countMoviesIntoCsvFile() { 
		  
	    try {
	    	
	    	File folder = ResourceUtils.getFile(AppConstants.PATH_CSV);
	        
	        for (final File fileEntry : folder.listFiles()) {
	        	
	        	if (fileEntry.isDirectory()) {
	                continue;
	            }
	        	return countLinesCsvFile(fileEntry.getPath());
	        }
	    } 
	    catch (Exception e) { 
	    	logger.error(e.getMessage());
	    }
		return null; 
	}
	
	public Integer countLinesCsvFile(String file) {
		Integer count = 0;
	    try {
	    	
	    	CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build(); // custom separator
	  
	        FileReader filereader = new FileReader(file);
	  
	        try (CSVReader csvReader = new CSVReaderBuilder(filereader)
	        		.withCSVParser(csvParser)
	    	        .withSkipLines(1)
    	          	.build()) {
				
				while (csvReader.readNext() != null) { 
					count++;
				}
			} 
	    } 
	    catch (Exception e) { 
	        logger.error(e.getMessage());
	    }
	    return count;
	}
	

}
