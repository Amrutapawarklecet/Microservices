package com.marlabs.training.cv.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.marlabs.training.cv.entity.Corona;
import com.marlabs.training.cv.repository.CoronaRepository;
import com.marlabs.training.cv.service.CoronaService;

@SpringBootTest
public class CoronaServiceTest {
	LocalDateTime dateCreated = LocalDateTime.now();

	@Autowired
	private CoronaService service;

	@MockBean
	private CoronaRepository repository;

	@Test
	public void CreateCoroa() {
		Corona c = new Corona();// create the object
		c.setActive(1);
		c.setConfirmed(3);
		c.setDeceased(1);
		c.setRecovered(3);
		c.setStatename("delhi");
		when(repository.save(c)).thenReturn(c);
		assertEquals(c, service.createNewState(c));
	}

	@Test
	public void getAllTest() {
		when(repository.findAll()).thenReturn(
				Stream.of(new Corona("delhi", 1, 3, 1, 3, dateCreated), new Corona("Goa", 3, 2, 3, 2, dateCreated))
						.collect(Collectors.toList()));
		assertEquals(2, service.findAll().size());
	}

	@Test
	public void getByStatenameTest() {
		when(repository.getBystatename("delhi")).thenReturn(new Corona("delhi", 1, 3, 1, 3, dateCreated));
		Corona c = service.getBystatename("delhi");
		assertEquals("delhi", c.getStatename());
		assertEquals(1, c.getActive());
		assertEquals(3, c.getConfirmed());
		assertEquals(1, c.getDeceased());
		assertEquals(3, c.getRecovered());
		assertEquals(dateCreated, c.getDateCreated());
	}

	// update
	@Test
	public void updateStateStatusTest() {
		Corona corona = new Corona("delhi", 4, 5, 6, 7, dateCreated);
		when(repository.getBystatename("delhi")).thenReturn(corona);
		when(repository.save(corona)).thenReturn(corona);
		Corona c = service.updateStateStatus(corona, corona.getStatename());
		int oldactive = c.getActive();
		int recovered = c.getRecovered();
		int deceased = c.getDeceased();
		c.setActive(corona.getActive() + oldactive);
		c.setRecovered(corona.getRecovered() + recovered);
		c.setDeceased(corona.getDeceased() + deceased);
		assertNotEquals(oldactive, c.getActive());
		assertNotEquals(recovered, c.getRecovered());
		assertNotEquals(deceased, c.getDeceased());

	}

}
