package com.example.moviesinfoservice.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.moviesinfoservice.models.Movie;


@RestController
@RequestMapping("/movies")
public class MovieService {

	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId){
		
		return new Movie(movieId, "Test name");
				
	}

}
