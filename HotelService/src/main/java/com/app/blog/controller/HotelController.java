package com.app.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.entities.Hotel;
import com.app.blog.sevices.HotelService;

@RestController
@RequestMapping("/api/hotels/")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	// post
	@PostMapping("/")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		Hotel createHotel = hotelService.createHotel(hotel);
		return new ResponseEntity<Hotel>(createHotel, HttpStatus.OK);
	}

	// get by id
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
		Hotel getHotel = hotelService.getHotel(hotelId);
		return new ResponseEntity<Hotel>(getHotel, HttpStatus.OK);
	}

	// get hotels
//	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/")
	public ResponseEntity<List<Hotel>> getAllHotels() {
		List<Hotel> hotels = hotelService.getHotels();

		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}


}
