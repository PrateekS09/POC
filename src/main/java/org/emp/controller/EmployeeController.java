package org.emp.controller;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.emp.model.Employee;
import org.emp.model.Error;
import org.emp.service.EmployeeService;
import org.emp.service.ExceptionService;
import org.emp.util.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ExceptionService exceptionService;
	

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST,headers = "Accept=application/xml")
	public ResponseEntity<String> addEmployee(@RequestBody String xmlPayLoad) {
		long startTime = System.currentTimeMillis();
		if(validateXmlPayload(xmlPayLoad)){
			Employee employee = parseXMLPayload(xmlPayLoad);
			if(null==employeeService.getEmplyee(employee.getId())) {
				employeeService.addEmployee(employee);
				HttpHeaders headers = new HttpHeaders();
				long endTime = System.currentTimeMillis();
				headers.add("TIME_TAKEN_MS", String.valueOf(endTime-startTime));
				return new ResponseEntity<String>( headers, HttpStatus.CREATED);
			} else {
				HttpHeaders headers = new HttpHeaders();
			    headers.add("ErrorMsg", ErrorConstants.HTTP_CODE_403_RESON);
			    return new ResponseEntity<String>( headers, HttpStatus.METHOD_NOT_ALLOWED);
			    }	
		}else{
			Error error = new Error();
			error.setErrorCode(ErrorConstants.HTTP_CODE_400);
			error.setErrorReason(ErrorConstants.HTTP_CODE_400_RESON);
			error.setXmlPayload(xmlPayLoad);
			exceptionService.addError(error);
			HttpHeaders headers = new HttpHeaders();
		    headers.add("ErrorMsg", ErrorConstants.HTTP_CODE_400_RESON);
		    return new ResponseEntity<String>( headers, HttpStatus.BAD_REQUEST);
		    
		}
	}

	protected Employee parseXMLPayload(String xmlPayLoad) {
		Employee employee = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			employee = (Employee) jaxbUnmarshaller.unmarshal(new StreamSource(new StringReader(xmlPayLoad)));

		  } catch (JAXBException e) {
			  System.out.println("Exception: "+e.getMessage());
        
		  }
		return employee;
	}

	protected boolean validateXmlPayload(String xmlPayLoad) {
		 try {
	         SchemaFactory factory =
	            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	            Schema schema = factory.newSchema(new File("/resources/Employee.xsd"));
	            Validator validator = schema.newValidator();
	            validator.validate(new StreamSource(new StringReader(xmlPayLoad)));
	      } catch (IOException e){
	         System.out.println("Exception: "+e.getMessage());
	         return false;
	      }catch(SAXException e1){
	         System.out.println("SAX Exception: "+e1.getMessage());
	         return false;
	      }
			
	      return true;
		    }
	
	

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT, headers = "Accept=application/xml")
	public ResponseEntity<String> updateEmployee(@RequestBody String xmlPayLoad) {
		long startTime = System.currentTimeMillis();
		if(validateXmlPayload(xmlPayLoad)){
			Employee employee = parseXMLPayload(xmlPayLoad);
			Employee existingEmp=employeeService.getEmplyee(employee.getId());
			if(null!=existingEmp) {
				if(notFrequentRequest(existingEmp)){
					employeeService.updateEmployee(employee);
					HttpHeaders headers = new HttpHeaders();
					long endTime = System.currentTimeMillis();
					headers.add("TIME_TAKEN_MS", String.valueOf(endTime-startTime));
					return new ResponseEntity<String>( headers, HttpStatus.ACCEPTED);	
				}else{
					Error error = new Error();
					error.setErrorCode(ErrorConstants.HTTP_CODE_501);
					error.setErrorReason(ErrorConstants.HTTP_CODE_501_RESON);
					error.setXmlPayload(xmlPayLoad);
					exceptionService.addError(error);
					HttpHeaders headers = new HttpHeaders();
				    headers.add("ErrorMsg", ErrorConstants.HTTP_CODE_501_RESON);
				    return new ResponseEntity<String>( headers, HttpStatus.ALREADY_REPORTED);
				}
				
			} else {
				HttpHeaders headers = new HttpHeaders();
			    headers.add("ErrorMsg", ErrorConstants.HTTP_CODE_403_RESON);
			    return new ResponseEntity<String>( headers, HttpStatus.METHOD_NOT_ALLOWED);
			    }	
		}else{
			Error error = new Error();
			error.setErrorCode(ErrorConstants.HTTP_CODE_400);
			error.setErrorReason(ErrorConstants.HTTP_CODE_400_RESON);
			error.setXmlPayload(xmlPayLoad);
			exceptionService.addError(error);
			HttpHeaders headers = new HttpHeaders();
		    headers.add("ErrorMsg", ErrorConstants.HTTP_CODE_400_RESON);
		    return new ResponseEntity<String>( headers, HttpStatus.BAD_REQUEST);
		    
		}
	}

	private boolean notFrequentRequest(Employee existingEmp) {
		long diffinhours = (new Date().getTime() - existingEmp.getUpdtedAt().getTime()) / (60 * 60 * 100);
		if (diffinhours > 2)
			return true;
		return false;
	}

}
