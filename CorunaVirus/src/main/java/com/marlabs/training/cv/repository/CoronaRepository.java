package com.marlabs.training.cv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marlabs.training.cv.entity.Corona;

@Repository
public interface CoronaRepository extends JpaRepository<Corona, String> {

	Corona getBystatename(String statename);

}
