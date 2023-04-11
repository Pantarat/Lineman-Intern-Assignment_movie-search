package com.wongnai.interview.movie.search;

import java.util.ArrayList;
import java.util.List;

import com.wongnai.interview.movie.InvIndexRepository;
import com.wongnai.interview.movie.InvertedIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.MovieSearchService;

@Component("invertedIndexMovieSearchService")
public class InvertedIndexMovieSearchService implements MovieSearchService {
	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private InvIndexRepository invIndexRepository;

	@Override
	public List<Movie> search(String queryText) {
		//TODO: Step 4 => Please implement in-memory inverted index to search movie by keyword.
		// You must find a way to build inverted index before you do an actual search.
		// Inverted index would looks like this:
		// -------------------------------
		// |  Term      | Movie Ids      |
		// -------------------------------
		// |  Star      |  5, 8, 1       |
		// |  War       |  5, 2          |
		// |  Trek      |  1, 8          |
		// -------------------------------
		// When you search with keyword "Star", you will know immediately, by looking at Term column, and see that
		// there are 3 movie ids contains this word -- 1,5,8. Then, you can use these ids to find full movie object from repository.
		// Another case is when you find with keyword "Star War", there are 2 terms, Star and War, then you lookup
		// from inverted index for Star and for War so that you get movie ids 1,5,8 for Star and 2,5 for War. The result that
		// you have to return can be union or intersection of those 2 sets of ids.
		// By the way, in this assignment, you must use intersection so that it left for just movie id 5.
		String[] keywords = queryText.split("\\s");
		List<List<Long>> allIdsForKeywords = new ArrayList<>();
		for (String keyword : keywords){
			List<InvertedIndex> results = invIndexRepository.search(keyword);
			List<Long> resultIds;
			if (results.isEmpty()) { resultIds = new ArrayList<>(); }
			else{
				resultIds = results.get(0).getMovieIds();
			}
			allIdsForKeywords.add(resultIds);
		}

		//check empty
		if(allIdsForKeywords.stream().allMatch(List::isEmpty))
			return new ArrayList<>();
		//intersect
		List<Long> intersection = new ArrayList<>(allIdsForKeywords.get(0));
		for (List<Long> ids: allIdsForKeywords.subList(1, allIdsForKeywords.size())){
			intersection.retainAll(ids);
		}
		//fetch movie
		List<Movie> movies = (List<Movie>) movieRepository.findAllById(intersection);

		return movies;
	}
}
