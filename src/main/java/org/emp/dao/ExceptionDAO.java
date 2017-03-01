package org.emp.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExceptionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public org.emp.model.Error addError(org.emp.model.Error error) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(error);
		return error;
	}
}
