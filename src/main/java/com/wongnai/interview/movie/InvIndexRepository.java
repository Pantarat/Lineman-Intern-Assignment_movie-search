package com.wongnai.interview.movie;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvIndexRepository extends CrudRepository<InvertedIndex, Long> {
    /**
     * Find movies from name using a given keyword.
     * <p>
     * The underlying database, HSQLDB, is store in data in case sensitive manner,
     * so LIKE operation in the query below will also compare with case sensitive
     * which may differ from another RDBMS such as MySQL, SQLServer.
     * <p>
     * Please check case sensitivity carefully.
     *
     * @param keyword
     * 		a user query keyword
     * @return list of movie
     */
    @Query("SELECT inv FROM InvertedIndex inv where inv.term = lower(:term)")
    List<InvertedIndex> search(@Param("term") String term);
    List<InvertedIndex> findByTerm(String term);
}
