package com.spring.javaEE.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid; 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.javaEE.model.Movie;
import com.spring.javaEE.model.ShoppingCart;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/movie")
@SessionAttributes("cart")
//@SessionAttributes("shoppingCart") 
//using variable name same as Type so don't need to annotate ModelAttribute and SessionAttribute

public class MovieController {

	//CreateSession attribute
	@ModelAttribute("cart")
	public ShoppingCart getCart() {
		log.info("Create new Shopping Cart");
		return new ShoppingCart();
	}
	
	/*
	 *  Just for example. 
	 *  In real world, we need to use service --> dao
	 */
	List<String> getMovieGenres(){
		List<String> genres = new ArrayList<>();
		
		genres.add("Action");
		genres.add("SuperNatural");
		genres.add("Slice-of-life");
		return genres;
	}
	
	@GetMapping	
	public String newMovie(Model model) {
		
		log.info("NewMovie controller");
		Movie movie = new Movie();
		//movie.setName("Infinity Train");
		
		List<String>genres = this.getMovieGenres();
		
		model.addAttribute("movie", movie);
		model.addAttribute("genres", genres);
		model.addAttribute("message","Minasan");
		
		return "movie";
	}
	
	@PostMapping
	public String createMovie(@Valid Movie movie,Errors errors,
												@SessionAttribute("cart")ShoppingCart cart) {
		
		if(errors.hasErrors()) {
			log.error("Got error in creating movie");
			return "movie";
		}
		
		else {
			cart.addMovie(movie);
			log.info(movie.getName());
			log.info("No. of movie in shopping cart "+cart.getMovies().size());
			return "movieList";
			//return "redirect:/";
		}
	}
}
