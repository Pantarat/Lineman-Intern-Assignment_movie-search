package com.wongnai.interview;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories


public class Application {
	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
