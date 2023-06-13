package com.catcareadmission.model;

import java.util.*;
  

public class Admission {
	
	private int patientId;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerEmail;
	private String ownerContact;
	private String catName; 
	private String catBreed;
	private String servicetoPerform;
	private Date admissionDate;
	

	
	public Admission(int patientId, String ownerFirstName, String ownerLastName, String ownerEmail, String ownerContact,
			String catName, String catBreed, String servicetoPerform, Date admissionDate) {
		super();
		this.patientId = patientId;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerEmail = ownerEmail;
		this.ownerContact = ownerContact;
		this.catName = catName;
		this.catBreed = catBreed;
		this.servicetoPerform = servicetoPerform;
		this.admissionDate = admissionDate;
	}
	
	public Admission( String ownerFirstName, String ownerLastName, String ownerEmail, String ownerContact,
			String catName, String catBreed, String servicetoPerform, Date admissionDate) {
		super();
		
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerEmail = ownerEmail;
		this.ownerContact = ownerContact;
		this.catName = catName;
		this.catBreed = catBreed;
		this.servicetoPerform = servicetoPerform;
		this.admissionDate = admissionDate;
	}


	public int getPatientId() {
		return patientId;
	}


	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}


	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getOwnerContact() {
		return ownerContact;
	}

	public void setOwnerContact(String ownerContact) {
		this.ownerContact = ownerContact;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatBreed() {
		return catBreed;
	}

	public void setCatBreed(String catBreed) {
		this.catBreed = catBreed;
	}

	public String getServicetoPerform() {
		return servicetoPerform;
	}

	public void setServicetoPerform(String servicetoPerform) {
		this.servicetoPerform = servicetoPerform;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}


	@Override
	public String toString() {
		return "Admission [patientId=" + patientId + ", ownerFirstName=" + ownerFirstName + ", ownerLastName="
				+ ownerLastName + ", ownerEmail=" + ownerEmail + ", ownerContact=" + ownerContact + ", catName="
				+ catName + ", catBreed=" + catBreed + ", servicetoPerform=" + servicetoPerform + ", admissionDate="
				+ admissionDate + "]";
	}
	
	

}
