package com.app.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.blog.entities.Rating;


@Repository
public interface RatingRepo extends JpaRepository<Rating, String> {

	// get rating by userId
	List<Rating> findByUserId(String userId);

	// get rating by hotelId
	List<Rating> findByHotelId(String hotelId);

}
