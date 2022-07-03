package com.spring.javaEE.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.javaEE.model.Genres;
import com.spring.javaEE.model.Movie;

@Repository
public class JdbcMovieRepository implements MovieRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Iterable<Movie> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Movie> findById(Long id) {
		// TODO Auto-generated method stub
		List<Movie> results = this.jdbcTemplate.query("SELECT * Movie where id=?", 
											this::mapRowToMovie,id);
		return results.isEmpty()?
				Optional.empty():
				Optional.of(results.get(0));
	}

	@Override
	public Movie save(Movie movie) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update("INSERT INTO movie(name, studio, genre, year, createAt, updateAt)"
				+ "VALUES (?,?,?,?,?,?);",
				movie.getName(),
				movie.getStudio(),
				movie.getGenre().toString(),
				movie.getYear(),
				movie.getCreateAt(),
				movie.getUpdateAt());
		
		return movie;
	}
	
	@SuppressWarnings("unused")
	private Movie mapRowToMovie(ResultSet row, int rowNum) throws SQLException{
		Movie movie = new Movie();
		movie.setId(row.getLong("id"));
		movie.setName(row.getString("name"));
		movie.setStudio(row.getString("studio"));
		movie.setYear(row.getLong("year"));
		movie.setGenre(Genres.valueOf(row.getString("genre")));
		
		movie.setCreateAt(row.getDate("createAt"));
		movie.setUpdateAt(row.getDate("updateAt"));
		return movie;
	}
}
