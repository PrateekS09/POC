package org.emp.service;

import java.util.Date;

import org.emp.dao.EmployeeDAO;
import org.emp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;
	

	
	@Transactional
	public Employee getEmplyee(int id) {
		return employeeDAO.getEmployee(id);
	}
	
	@Transactional
	public void addEmployee(Employee employee) {
		employee.setUpdtedAt(new Date());
		employeeDAO.addEmployee(employee);
	}

	@Transactional
	public void updateEmployee(Employee employee) {
		employee.setUpdtedAt(new Date());
		employeeDAO.updateEmployee(employee);

	}

	@Transactional
	public void deleteEmployee(int id) {
		employeeDAO.deleteEmployee(id);
	}
}
