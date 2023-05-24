package com.app.blog.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.blog.entities.Hotel;
import com.app.blog.entities.Hotels;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {
	
	@GetMapping("/api/hotels/{hotelId}")
	Hotels getHotel(@PathVariable("hotelId") String hotelId);

}
