package org.emp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * This is our model class and it corresponds to Employee table in database
 */
@Entity
@Table(name="EMPLOYEE")
@XmlRootElement(name="Employee")
public class Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Column(name="employeeName")
	String employeeName;
	
	@Column(name="employeeDOJ")
	Date employeeDOJ;

	@Column(name="employeeDept")
	String employeeDept;

	@Column(name="updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
	Date updtedAt;

	public Date getUpdtedAt() {
		return updtedAt;
	}
	public void setUpdtedAt(Date updtedAt) {
		this.updtedAt = updtedAt;
	}
	public Employee() {
		super();
	}
	public Employee(int i, String employeeName, Date employeeDOJ, String employeeDept) {
		super();
		this.id = i;
		this.employeeName = employeeName;
		this.employeeDOJ = employeeDOJ;
		this.employeeDept = employeeDept;
	}
	
	@XmlElement(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name="employeeName")
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	@XmlElement(name="employeeDOJ")
	public Date getEmployeeDOJ() {
		return employeeDOJ;
	}
	public void setEmployeeDOJ(Date employeeDOJ) {
		this.employeeDOJ = employeeDOJ;
	}
	

	@XmlElement(name="employeeDept")
	public String getEmployeeDept() {
		return employeeDept;
	}
	public void setEmployeeDept(String employeeDept) {
		this.employeeDept = employeeDept;
	}

}