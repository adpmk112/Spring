package com.spring.javaEE.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data

public class Movie {
	
	private Long id;
	
	@NotNull
	@Size(min=5,message="Name must be at least 5 chracters long")
	private String name;
	
	@NotNull
	@Size(min=5,message="Studio must be at least 5 chracters long")
	private String studio;
	
	@NotNull
	//@Size(min=4,message="Year must be at least 4 digit long") 
	// String type is ok to use @Size but long type is not
	@Range(min=1995,max=2022,message="Year must be between 1995 and 2022")
	private Long year;
	
	private Genres genre;
	
	private Date createAt = new Date();
	
	private Date updateAt = new Date();
}
