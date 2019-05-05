package com.example.ratingdataservice.resources;

import com.example.ratingdataservice.model.Rating;
import com.example.ratingdataservice.model.UserRatings;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingService {
	
	 @RequestMapping("{movieId}")
	    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
	        return new Rating(movieId, 4);
	    }
	 
	 @RequestMapping("/users/{userId}")
	    public UserRatings getUserRating(@PathVariable("userId") String userId) {
		 
		 List<Rating> ratings = Arrays.asList(
					new Rating ("M1234", 4),
					new Rating("M5678", 3)
				);
		 
		 UserRatings userRating = new UserRatings();
		 userRating.setUserRatings(ratings);
		 
		 return userRating;
	    }
}
