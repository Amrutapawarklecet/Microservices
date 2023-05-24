package com.app.blog.services.impl;

import java.util.List;
import java.util.UUID;

import org.apache.coyote.UpgradeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.blog.entities.Rating;
import com.app.blog.exceptions.ResourceNotFoundExpection;
import com.app.blog.repositories.RatingRepo;
import com.app.blog.sevices.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepo ratingRepo;

	@Override
	public Rating createRating(Rating rating) {
		String randomId = UUID.randomUUID().toString();
		rating.setRatingId(randomId);
		Rating createRating = ratingRepo.save(rating);
		return createRating;
	}

	@Override
	public Rating getRatingById(String ratingId) {
		Rating getRating = ratingRepo.findById(ratingId).orElseThrow(()->
		new ResourceNotFoundExpection("Rating", "id", ratingId));
		return getRating;
	}

	@Override
	public List<Rating> getAllRatings() {
		List<Rating> getAllRatings = ratingRepo.findAll();
		return getAllRatings;
	}
	
	@Override
	public List<Rating> getRatingsByUserId(String userId) {
		List<Rating> getRatingByUser = ratingRepo.findByUserId(userId);
		return getRatingByUser;
	}
	
	@Override
	public List<Rating> getRatingsByHotelId(String hotelId) {
		List<Rating> getRatingByHotel = ratingRepo.findByHotelId(hotelId);
		return getRatingByHotel;
	}


}
