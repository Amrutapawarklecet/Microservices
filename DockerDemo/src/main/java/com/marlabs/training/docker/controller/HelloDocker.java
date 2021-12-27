package com.marlabs.training.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloDocker {

	@GetMapping
	public String sayHello() {
		return "Hello";
		
	}
}
