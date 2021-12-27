package com.marlabs.training.departmeant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlabs.training.departmeant.entity.Department;
import com.marlabs.training.departmeant.repository.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j	
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
    
	public Department saveDepartment(Department department) {
		log.info("Inside save Department of departmentService");
		return departmentRepository.save(department);
	}

	public Department findDepartmentById(Long departmentId) {
		log.info("Inside findDepartmentById Department of departmentService");
		return departmentRepository.findByDepartmentId(departmentId);
	}

}
