package com.wongnai.interview.movie.sync;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component("invertedIndexInitializer")
@DependsOn("movieDatabaseInitializer")
public class InvertedIndexInitializer implements InitializingBean {
    @Autowired
    private InvertedIndexSynchronizer invertedIndexSynchronizer;

    @Override
    public void afterPropertiesSet()  {
        //run sync while server is starting
        invertedIndexSynchronizer.fillInvIndex();
    }
}