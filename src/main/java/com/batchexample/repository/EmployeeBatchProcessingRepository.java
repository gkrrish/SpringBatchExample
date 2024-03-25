package com.batchexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.batchexample.annotations.ForBatchProcessing;
import com.batchexample.entity.Employee;

@Repository
public interface EmployeeBatchProcessingRepository extends JpaRepository<Employee, Long> {
	@ForBatchProcessing
	@Query("SELECT e FROM Employee e ORDER BY e.id ASC")
	public List<Employee> findEmployeesForBatch(@Param("chunkSize") int chunkSize);
}
