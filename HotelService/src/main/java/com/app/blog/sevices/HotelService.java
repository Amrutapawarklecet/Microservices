package com.app.blog.sevices;

import java.util.List;

import com.app.blog.entities.Hotel;


public interface HotelService {

	// post
	Hotel createHotel(Hotel hotel);

	// get using id
	Hotel getHotel(String hotelId);

	// get all data
	List<Hotel> getHotels();

}
