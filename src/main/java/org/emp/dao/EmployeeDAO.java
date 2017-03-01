package org.emp.dao;


import org.emp.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}


	public Employee addEmployee(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(employee);
		return employee;
	}
	
	public Employee getEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee employee = (Employee) session.get(Employee.class, new Integer(id));
		return employee;

	}

	public void updateEmployee(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(employee);
	}

	public void deleteEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee p = (Employee) session.load(Employee.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}	
}
