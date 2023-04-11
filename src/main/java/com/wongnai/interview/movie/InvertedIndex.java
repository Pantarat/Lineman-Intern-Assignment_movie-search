package com.wongnai.interview.movie;

import java.util.List;

import javax.persistence.*;

@Entity
public class InvertedIndex {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String term;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "inverted_index_movie_ids",
                    joinColumns = @JoinColumn(name = "inverted_index_id"))
    private List<Long> movieIds;

    protected InvertedIndex() {
    }

    public InvertedIndex(String term, List<Long> movieIds) {
        this.term = term;
        this.movieIds = movieIds;
    }

    public Long getId() {
        return id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public List<Long> getMovieIds(){
        return movieIds;
    }

    public void setMovieIds(List<Long> movieIds){
        this.movieIds = movieIds;
    }

}




