package org.emp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Date;

/*
 * This is our model class and it corresponds to Employee table in database
 */
@Entity
@Table(name="ERROR")
public class Error implements Serializable {
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorReason() {
		return errorReason;
	}
	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}
	public String getXmlPayload() {
		return xmlPayload;
	}
	public void setXmlPayload(String xmlPayload) {
		this.xmlPayload = xmlPayload;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@Column(name="errorCode")
	int errorCode;
	
	@Column(name="errorReason")
	String errorReason;

	@Column(name="xmlPayload")
	String xmlPayload;
	public Error() {
		super();
	}
}