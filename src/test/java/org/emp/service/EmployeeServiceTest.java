package org.emp.service;

import static org.junit.Assert.*;

import org.emp.dao.EmployeeDAO;
import org.emp.model.Employee;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-servlet.xml"})
@WebAppConfiguration

public class EmployeeServiceTest {

	@Autowired
	EmployeeService employeeServiceMock;
	
	@Mock
	EmployeeDAO employeeDAOmock;

	@Mock 
	Session session;
	Employee employeeGlobal;
	
	@Before
	public void setup(){
	employeeGlobal = new Employee();
	employeeGlobal.setId(1);
	employeeGlobal.setEmployeeName("John");
	employeeGlobal.setEmployeeDept("FIN");
	
	Mockito.when(employeeDAOmock.getEmployee(1)).thenReturn(employeeGlobal);
	Mockito.when(employeeDAOmock.addEmployee(employeeGlobal)).thenReturn(employeeGlobal);
	}

	@Test
	public void getEmployeeValidate(){
		Employee employee = employeeDAOmock.getEmployee(1);
		assertEquals("John", employee.getEmployeeName());
		assertEquals("FIN", employee.getEmployeeDept());
	}
	
	@Test
	public void addEmployeeValidate(){
		Employee employee = employeeDAOmock.addEmployee(employeeGlobal);
		assertEquals(employee, employeeGlobal);
	}
	
	@Test
	public void updateEmployeeValidate(){
		employeeDAOmock.updateEmployee(employeeGlobal);
		Mockito.verify(session).update(employeeGlobal);
	}
}
