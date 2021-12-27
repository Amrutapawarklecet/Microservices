package com.marlabs.training.departmeant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marlabs.training.departmeant.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long>  {

	Department findByDepartmentId(Long departmentId);

}
