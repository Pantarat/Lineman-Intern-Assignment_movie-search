package com.wongnai.interview.movie.sync;

import javax.transaction.Transactional;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MoviesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieDataService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDataSynchronizer {
	@Autowired
	private MovieDataService movieDataService;

	@Autowired
	private MovieRepository movieRepository;

	@Transactional
	public void forceSync() {
		//TODO: implement this to sync movie into repository

		MoviesResponse allmovie = movieDataService.fetchAll();
		List<Movie> insert_values = new ArrayList<Movie>();
		for (MovieData moviedata : allmovie) {
			Movie movie = new Movie(moviedata.getTitle());
			movie.setActors(moviedata.getCast());
			insert_values.add(movie);
		}
		movieRepository.saveAll(insert_values);
	}
}
