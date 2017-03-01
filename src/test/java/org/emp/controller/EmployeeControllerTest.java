package org.emp.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.emp.model.Employee;
import org.emp.service.EmployeeService;
import org.emp.service.ExceptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-servlet.xml"})
@WebAppConfiguration

public class EmployeeControllerTest {

	private MockMvc mockMvc;
	
	EmployeeController controller = new  EmployeeController();
	
	@Mock
	private EmployeeService employeeServiceMock;
	
	@Mock
	private ExceptionService exceptionServiceMock;

	@Mock
	private Employee employeeMock;
	
	private void setup() {
		
	}
	
	 @Test
	 public void test_good_payload() {
		 String validxmlpayload = "<Employee><id>1</id><employeeName>Ivan</employeeName><employeeDOJ>2017-01-31</employeeDOJ><employeeDept>IT</employeeDept></Employee>";
		 assertTrue(controller.validateXmlPayload(validxmlpayload));
	 }
	 
	 @Test
	 public void test_bad_payload() {
		 EmployeeController controller = new  EmployeeController();
		 String validxmlpayload = "<Employee>1</id><employeeName>Ivan</employeeName><employeeDOJ>2017-01-31</employeeDOJ><employeeDept>IT</employeeDept></Employee>";
		 assertFalse(controller.validateXmlPayload(validxmlpayload));
	 }
	 
	 @Test
	 public void test_good_payload_parsing(){
		 String validxmlpayload = "<Employee><id>1</id><employeeName>Ivan</employeeName><employeeDOJ>2017-01-31</employeeDOJ><employeeDept>IT</employeeDept></Employee>";
		 assertTrue(controller.parseXMLPayload(validxmlpayload) instanceof Employee);
	 }
	 
	 @Test
	 public void test_bad_payload_parsing(){
		 String validxmlpayload = "<Emploee><id>1</id><employeeName>Ivan</employeeName><employeeDOJ>2017-01-31</employeeDOJ><employeeDept>IT</employeeDept></Employee>";
		 Assert.assertNull(controller.parseXMLPayload(validxmlpayload));
	 }
	 
	 
	 

}
