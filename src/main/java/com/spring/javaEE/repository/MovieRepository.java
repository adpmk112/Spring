package com.spring.javaEE.repository;

import java.util.Optional;

import com.spring.javaEE.model.Movie;

public interface MovieRepository {
	Iterable<Movie>findAll();
	Optional<Movie> findById(Long id);
	Movie save(Movie movie);
}
