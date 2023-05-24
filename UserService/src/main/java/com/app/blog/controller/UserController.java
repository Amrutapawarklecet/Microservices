package com.app.blog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.blog.entities.User;
import com.app.blog.sevices.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/api/users/")
public class UserController {

	@Autowired
	private UserService userService;
	
    private Logger logger = LoggerFactory.getLogger(UserController.class);


	// post
	@PostMapping("/")
	public ResponseEntity<User> creatUser(@RequestBody User user) {

		User createUser = userService.createUser(user);
		return new ResponseEntity<User>(createUser, HttpStatus.OK);
	}

	int retryCount=1;

	// get by id
	@GetMapping("/{userId}")
	
	//CircuitBreaker of resilience4j
	/*@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod="ratingHotelFallback")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {
		logger.info("Get single user handler: UserController");
		User userById = userService.getUserById(userId);
		return new ResponseEntity<User>(userById, HttpStatus.OK);
	}*/
	
	//Retry of resilience4j
	/*@Retry(name="ratingHotelService",fallbackMethod="ratingHotelFallback")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {
		logger.info("Get single user handler: UserController");
		logger.info("Retry count: {}",retryCount);
		retryCount++;
		User userById = userService.getUserById(userId);
		return new ResponseEntity<User>(userById, HttpStatus.OK);
	}
	*/
	
	@RateLimiter(name="userRateLimiter",fallbackMethod="ratingHotelFallback")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {
		logger.info("Get single user handler: UserController");
		logger.info("Retry count: {}",retryCount);
		retryCount++;
		User userById = userService.getUserById(userId);
		return new ResponseEntity<User>(userById, HttpStatus.OK);
	}
	
	//creating fall back method for circuitbreaker
	/*public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
		logger.info("Fallback is executed because service is down:", ex.getMessage());
		
		User user = User.builder()
		.email("dummy@gmail.com")
		.name("Dummy")
		.about("This user is created dummy because some service is down")
		.userId("141234")
		.build();
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}*/
	
	//creating fall back method for retry or RateLimiter
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
//		logger.info("Fallback is executed because service is down:", ex.getMessage());
		
		ex.printStackTrace();

		User user = User.builder()
		.email("dummy@gmail.com")
		.name("Dummy")
		.about("This user is created dummy because some service is down")
		.userId("141234")
		.build();
		return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
		
	}

	// get all users
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}


}
