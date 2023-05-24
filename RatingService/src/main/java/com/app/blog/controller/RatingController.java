package com.app.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.app.blog.entities.Rating;
import com.app.blog.sevices.RatingService;

@RestController
@RequestMapping("/api/ratings/")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	// post
	@PostMapping("/")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		Rating createRating = ratingService.createRating(rating);

		return new ResponseEntity<Rating>(createRating, HttpStatus.OK);
	}

	// get by id
	@GetMapping("/{ratingId}")
	public ResponseEntity<Rating> getRatingById(@PathVariable String ratingId) {
		Rating ratingById = ratingService.getRatingById(ratingId);
		return new ResponseEntity<Rating>(ratingById, HttpStatus.OK);
	}

	// get all
	@GetMapping("/")
	public ResponseEntity<List<Rating>> getAllRatings() {
		List<Rating> allRatings = ratingService.getAllRatings();
		return new ResponseEntity<List<Rating>>(allRatings, HttpStatus.OK);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
		List<Rating> ratingByUserId = ratingService.getRatingsByUserId(userId);
		return new ResponseEntity<List<Rating>>(ratingByUserId, HttpStatus.OK);
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
		List<Rating> ratingByHotelId = ratingService.getRatingsByHotelId(hotelId);
		return new ResponseEntity<List<Rating>>(ratingByHotelId, HttpStatus.OK);
	}



}
