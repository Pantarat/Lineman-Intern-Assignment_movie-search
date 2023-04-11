package com.wongnai.interview.movie.sync;

import javax.transaction.Transactional;

import com.wongnai.interview.movie.InvertedIndex;
import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.InvIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.MovieRepository;

import java.util.*;

@Component
public class InvertedIndexSynchronizer {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private InvIndexRepository invIndexRepository;

    @Transactional
    public void fillInvIndex() {

        Iterable<Movie> allmovie = movieRepository.findAll();
        Map<String, List<Long>> invertedIndexData = new HashMap<>();
        for (Movie movie : allmovie) {
            String[] terms = movie.getName().split("\\s+");
            for (String term : terms) {
                term = term.toLowerCase();
                List<Long> movieIds = invertedIndexData.computeIfAbsent(term, k -> new ArrayList<>());
                if (!movieIds.contains(movie.getID())) {
                    movieIds.add(movie.getID());
                }
            }
        }
        List<InvertedIndex> invertedIndexEntities = new ArrayList<>();
        for (Map.Entry<String, List<Long>> entry : invertedIndexData.entrySet()) {
            InvertedIndex invertedIndex = new InvertedIndex(entry.getKey(), entry.getValue());
            invertedIndexEntities.add(invertedIndex);
        }
        invIndexRepository.saveAll(invertedIndexEntities);
    }
}


