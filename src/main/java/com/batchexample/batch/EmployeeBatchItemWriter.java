/*
 * package com.batchexample.batch;
 * 
 * import java.util.List;
 * 
 * import org.springframework.batch.item.Chunk; import
 * org.springframework.batch.item.ItemWriter; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Component;
 * 
 * import com.batchexample.entity.Employee; import
 * com.batchexample.repository.EmployeeRepository;
 * 
 * @Component public class EmployeeBatchItemWriter implements
 * ItemWriter<List<Employee>> {
 * 
 * @Autowired private EmployeeRepository employeeRepository;
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @Override public void write(Chunk<? extends List<Employee>> employees) throws
 * Exception { employees.forEach(employeeList -> { if (employeeList instanceof
 * List) { employeeRepository.saveAllAndFlush(employeeList);
 * 
 * } else if (employeeList instanceof Chunk) { List<Employee> items =
 * ((Chunk<Employee>) employeeList).getItems();
 * employeeRepository.saveAllAndFlush(items);
 * 
 * } else { throw new IllegalArgumentException("Unexpected type for employees: "
 * + employeeList.getClass()); } }); }
 * 
 * }
 */