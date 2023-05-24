package com.app.blog.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.blog.entities.Hotel;
import com.app.blog.exceptions.ResourceNotFoundExpection;
import com.app.blog.repositories.HotelRepo;
import com.app.blog.sevices.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepo hotelRepo;

	@Override
	public Hotel createHotel(Hotel hotel) {
		String randomId = UUID.randomUUID().toString();
		hotel.setHotelId(randomId);
		Hotel createHotel = hotelRepo.save(hotel);
		return createHotel;
	}

	@Override
	public Hotel getHotel(String hotelId) {
		Hotel getHotel = hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundExpection("Hotel", "id", hotelId));
		return getHotel;
	}

	@Override
	public List<Hotel> getHotels() {
		List<Hotel> findAllHotels = hotelRepo.findAll();

		return findAllHotels;
	}

}
