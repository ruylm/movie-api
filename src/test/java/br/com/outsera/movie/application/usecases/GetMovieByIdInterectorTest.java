package br.com.outsera.movie.application.usecases;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.outsera.movie.domain.entity.Movie;

@SpringBootTest
public class GetMovieByIdInterectorTest {
	
	@Autowired
	private LoadCsvInterector loadCsvInterector;
	
	@Autowired
	private GetMovieByIdInterector getMovieByIdInterector;
	
	
	@Test
	public void filmesCadastradodosConformeCsv() {
		
		List<Movie> movieListFile = loadCsvInterector.readLimitedFilesFromResourceFolder(10);
		
		for(Movie movieFile :movieListFile) {
			
			Movie movieBD = getMovieByIdInterector.getMovieByTitle(movieFile.getTitle());
			
			Assertions.assertEquals(movieFile.getTitle(), movieBD.getTitle());
			
		}
		
	}
	
	@Test
	public void quantidadeDeFilmesCadastrados() {
		
		Integer countCsv = loadCsvInterector.countMoviesIntoCsvFile();
		
		Integer countBD = getMovieByIdInterector.getCountMovies();

		Assertions.assertEquals(countCsv, countBD);
		
	}

}
