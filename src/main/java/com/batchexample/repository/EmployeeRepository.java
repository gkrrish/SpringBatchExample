package com.batchexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.batchexample.annotations.ForBatchProcessing;
import com.batchexample.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@ForBatchProcessing
	@Query("SELECT e FROM Employee e ORDER BY e.id ASC")
	List<Employee> readPage(@Param("chunkSize") int chunkSize);
	
	//findAllByIdGreaterThanOrderByIdAsc
}