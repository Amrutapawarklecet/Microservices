package com.marlabs.training.cv.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.marlabs.training.cv.entity.Corona;
import com.marlabs.training.cv.repository.CoronaRepository;

@Service
public class CoronaService {

	@Autowired
	private CoronaRepository coronaRepository;

	public Corona createNewState(Corona corona) {
		return coronaRepository.save(corona);
	}

	public List<Corona> findAll() {
		return coronaRepository.findAll();
	}

	public Corona getBystatename(String statename) {
		Corona corona = coronaRepository.getBystatename(statename);
		return corona;

	}

	public Corona updateStateStatus(Corona corona, String statename) {
		Corona existing = coronaRepository.getBystatename(statename);
		int oldactive = existing.getActive();
		int recovered = existing.getRecovered();
		int deceased = existing.getDeceased();
		existing.setActive(corona.getActive() + oldactive);
		existing.setRecovered(corona.getRecovered() + recovered);
		existing.setDeceased(corona.getDeceased() + deceased);
		return coronaRepository.save(existing);
	}
}
