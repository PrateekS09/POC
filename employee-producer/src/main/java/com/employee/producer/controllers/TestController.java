package com.employee.producer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.producer.model.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class TestController {

	private int empId = 0;

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getDataFallBack")
	public Employee firstPage() {
		empId++;
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId(empId + "");
		emp.setSalary(3000 + empId);
		
		if (empId > 5) {
			throw new RuntimeException();
		}

		return emp;
	}

	public Employee getDataFallBack() {

		Employee emp = new Employee();
		emp.setName("DefaultEmployee");
		emp.setDesignation("DefaultDesignation");
		emp.setEmpId("0000");
		emp.setSalary(0000);

		return emp;

	}

}