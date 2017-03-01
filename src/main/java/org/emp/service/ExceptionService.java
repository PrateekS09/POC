package org.emp.service;

import java.util.Date;

import org.emp.dao.EmployeeDAO;
import org.emp.dao.ExceptionDAO;
import org.emp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("exceptionService")
public class ExceptionService {

	@Autowired
	 ExceptionDAO exceptionDAO;
	


	@Transactional
	public void addError(org.emp.model.Error error) {
		exceptionDAO.addError(error);
	}

}
