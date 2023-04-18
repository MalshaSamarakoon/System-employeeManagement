package com.example.EmployeeManagementSystem;

import com.example.EmployeeManagementSystem.controller.RestClient;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class EmployeeManagementSystemApplicationTests {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void saveEmployeeTest() {
		Employee employee = new Employee();
		employee.setFirstName("Wayne");
		employee.setLastName("Rigsby");
		employee.setEmailId("rigsby@gmail.com");

		employeeRepository.save(employee);

		Assertions.assertThat(employee.getId()).isGreaterThan(0);
	}

	@Test
	public void getEmployeeTest() {
		Employee employee = employeeRepository.findById(2L).get();
		Assertions.assertThat(employee.getId()).isEqualTo(2L);
	}
}