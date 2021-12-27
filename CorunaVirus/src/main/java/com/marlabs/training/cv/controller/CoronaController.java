package com.marlabs.training.cv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marlabs.training.cv.entity.Corona;
import com.marlabs.training.cv.service.CoronaService;

@RestController
@RequestMapping(value = "/covid")
public class CoronaController {
	
	@Autowired
	private CoronaService coronaService;
	
	@PostMapping(value = "/")
	public Corona createNewState(@RequestBody Corona corona ) {
	return coronaService.createNewState(corona);
	}
	
	
	@GetMapping(value="/")
	public List<Corona> getAllState() {
	return coronaService.findAll();
	}
	
	@GetMapping("/{statename}")
	public Corona getBystatename(@PathVariable("statename") String statename) {
	return coronaService.getBystatename(statename); }

	
	@PutMapping("/{statename}")
	public Corona updateStateStatus(@RequestBody Corona corona, @PathVariable ("statename") String statename) {
	return coronaService.updateStateStatus(corona,statename);
	}



}
