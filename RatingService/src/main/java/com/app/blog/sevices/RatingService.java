package com.app.blog.sevices;

import java.util.List;

import com.app.blog.entities.Rating;


public interface RatingService {

	// post
	Rating createRating(Rating rating);


	// get using ratingId
	Rating getRatingById(String ratingId);

	// get all 
	List<Rating> getAllRatings();
 
	//get all by userId
	List<Rating> getRatingsByUserId(String userId);
	
	//get all by hotelId
	List<Rating> getRatingsByHotelId(String hotelId);


}
