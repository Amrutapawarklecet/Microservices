package com.app.blog.sevices;

import java.util.List;

import com.app.blog.entities.User;



public interface UserService {

	// post
	User createUser(User user);

	// get using id
	User getUserById(String userId);
	
	// get all data
	List<User> getAllUsers();
	

}
