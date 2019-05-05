package com.example.moviescatelogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.moviescatelogservice.model.CatalogItem;
import com.example.moviescatelogservice.model.Movie;
import com.example.moviescatelogservice.model.Rating;
import com.example.moviescatelogservice.model.UserRatings;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	//Create RestTemplate instance
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		//Get all rated movie Ids
		UserRatings ratings = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/"+ userId, UserRatings.class);
		
		
		System.out.println("ratings "+ ratings.getUserRatings().size() );
		
		//Using RestTemplate to make API call
		return ratings.getUserRatings().stream().map(rating -> {
			
			//for each movie Id, call movie info service and get details.
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class);
			
			System.out.println("moviename" + movie.getName()+ " Movierating "+ rating.getRating());
			//put all them together
			return new CatalogItem(movie.getName(), "Test", rating.getRating());
		})
		.collect(Collectors.toList());
		
		
		
		//Using WebClient to make API call
		
		/*
		return ratings.stream().map(rating -> {
			
			//To get an instance of movie class
			
			 1. write a method you want to use
			 2. provide uri over which u wnat to access data
			 3.call retrieve() function to retrieve data 
			 4.bodyToMono(provide the instance type) : it says that whatever u gets in a response body convert that 
			 	into a instance type passed as an argument.
			 5.call block() method which will block the execution till will get the response back from the bodyToMono()
			 
			
			Movie movie = webClientBuilder.build()
							.get()
							.uri("http://localhost:8082/movies/"+ rating.getMovieId())
							.retrieve()
							.bodyToMono(Movie.class)
							.block();
			
			return new CatalogItem(movie.getName(), "Test", rating.getRating());
			
		})
				.collect(Collectors.toList());
		*/
			
	}
}
