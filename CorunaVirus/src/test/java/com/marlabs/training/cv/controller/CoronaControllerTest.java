package com.marlabs.training.cv.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marlabs.training.cv.entity.Corona;
import com.marlabs.training.cv.service.CoronaService;

@WebMvcTest(CoronaController.class)
public class CoronaControllerTest {

	LocalDateTime dateCreated = LocalDateTime.now();
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private CoronaService service;

	// get corona
	@Test
	public void getAllStateTest() throws Exception {
		List<Corona> corona = new ArrayList<>();
		corona.add(new Corona("delhi", 2, 3, 2, 3, dateCreated));
		corona.add(new Corona("Goa", 3, 2, 3, 2, dateCreated));
		when(service.findAll()).thenReturn(corona);
		String url = "/covid/";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andDo(print()).andReturn();
		String actualResult = mvcResult.getResponse().getContentAsString();
		System.out.println(actualResult);
		String expectedResult = objectMapper.writeValueAsString(corona);
		assertThat(actualResult).isEqualToIgnoringWhitespace(expectedResult);
	}

	// create corona
	@Test
	public void createNewStateTest() throws JsonProcessingException, Exception {
		Corona newCorona = new Corona("delhi", 2, 3, 2, 3, dateCreated);
		Corona saveCorona = new Corona("Goa", 3, 2, 3, 2, dateCreated);
		when(service.createNewState(newCorona)).thenReturn(saveCorona);
		String url = "/covid/";
		mockMvc.perform(post(url).contentType("application/json").content(objectMapper.writeValueAsBytes(newCorona))
				.with(csrf())).andExpect(status().isOk()).andDo(print());
	}

	// create ByStateName
	@Test
	public void getBystatenameTest() throws Exception {
		Corona corona = new Corona("delhi", 2, 3, 2, 3, dateCreated);
		when(service.getBystatename("Delhi")).thenReturn(corona);
		String url = "/covid/{statename}";
		mockMvc.perform(get(url, "Delhi")).andExpect(status().isOk()).andDo(print()).andReturn();
	}

	// update
	@Test
	public void updateStateStatusTest() throws JsonProcessingException, Exception {

		Corona corona = new Corona("Goa", 1, 7, 8, 9, dateCreated);
		when(service.getBystatename("Goa")).thenReturn(corona);
		when(service.updateStateStatus(corona, "Goa")).thenReturn(corona);
		Corona c = service.updateStateStatus(corona, corona.getStatename());

		int oldactive = c.getActive();
		int recovered = c.getRecovered();
		int deceased = c.getDeceased();
		c.setActive(corona.getActive() + oldactive);
		c.setRecovered(corona.getRecovered() + recovered);
		c.setDeceased(corona.getDeceased() + deceased);

		when(service.updateStateStatus(c, "Goa")).thenReturn(c);
		String url = "/covid/{statename}";
		mockMvc.perform(put(url, "Delhi").contentType("application/json").content(objectMapper.writeValueAsBytes(c))
				.with(csrf())).andExpect(status().isOk()).andDo(print());
	}

}
