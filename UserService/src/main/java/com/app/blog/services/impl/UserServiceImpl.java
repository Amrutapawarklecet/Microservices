package com.app.blog.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.blog.entities.Rating;
import com.app.blog.entities.User;
import com.app.blog.entities.Hotels;
import com.app.blog.exceptions.ResourceNotFoundExpection;
import com.app.blog.external.services.HotelService;
import com.app.blog.repositories.UserRepo;
import com.app.blog.sevices.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
    private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


	@Override
	public User createUser(User user) {

		// generate unique id
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		User createUser = userRepo.save(user);
		return createUser;
	}

	// get single user
	@Override
	public User getUserById(String userId) {

		// single user
		User getUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundExpection("User", "id", userId));

		// http://localhost:8083/api/ratings/users/9be468dc-d756-468d-9d98-ea0b2cb09b7f
		// get rating services
		/*
		 * ArrayList<Rating> ratingOfUser = restTemplate
		 * .getForObject("http://localhost:8083/api/ratings/users/" +
		 * getUser.getUserId(), ArrayList.class);
		 */

		Rating[] ratingOfUser = restTemplate
				.getForObject("http://RATING-SERVICE/api/ratings/users/" + getUser.getUserId(), Rating[].class);

		logger.info("{}", ratingOfUser);
		List<Rating> ratings = Arrays.stream(ratingOfUser).collect(Collectors.toList());

		List<Rating> ratingList = ratings.stream().map(rating -> {

			// api call to hotel service to get the hotel
//			http://localhost:8082/api/hotels/2f8db861-e6aa-434f-88d2-ba854beaee48

			/*
			 * ResponseEntity<Hotels> forEntity = restTemplate
			 * .getForEntity("http://localhost:8083/api/ratings/hotels/" +
			 * rating.getHotelId(), Hotels.class);
			 */

			/*Hotels[] forObject = restTemplate
					.getForObject("http://HOTEL-SERVICE/api/hotels/" + rating.getHotelId(), Hotels[].class);*/

			/*
			 * Hotels hotel = forObject.getBody();
			 * 
			 * logger.info("response status code: {}", forObject.getStatusCode());
			 */

			// set the hotel to rating
			
			Hotels hotel=hotelService.getHotel(rating.getHotelId());
/*			List<Hotels> hotel = Arrays.stream(forObject).collect(Collectors.toList());
*/
			rating.setHotel(hotel);

			// return the rating
			return rating;

		}).collect(Collectors.toList());

		getUser.setRating(ratingList);

		return getUser;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> findAllUsers = userRepo.findAll();
		return findAllUsers;
	}

}
