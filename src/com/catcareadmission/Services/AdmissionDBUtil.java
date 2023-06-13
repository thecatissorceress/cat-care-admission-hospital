package com.catcareadmission.Services;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.catcareadmission.model.*;

public class AdmissionDBUtil {
	
	private DataSource dataSource;
	
	
	
	
	public AdmissionDBUtil() {
		
	}

	public AdmissionDBUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Admission> getAdmission() throws Exception {


		List<Admission> patient = new ArrayList<>();
		
		// Create Object Connection
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		 try {
		// get a connection 
		myConn = dataSource.getConnection();
		// create sql statement
		String sql = "select * from admission order by patient_id";
		
		
		myStmt = myConn.createStatement();
		// execute query
		myRs = myStmt.executeQuery(sql);
		
	    // process result set 
		 while(myRs.next()) {
			 //retrieve data from result row 
			 int patientId = myRs.getInt("patient_id");
			 String ownerFirstName = myRs.getString("owner_first_name");
			 String ownerLastName = myRs.getString("owner_last_name");
			 String ownerEmail = myRs.getString("owner_email");
			 String ownerContact = myRs.getString("owner_contact");
			 String catName = myRs.getString("cat_name");
			 String catBreed = myRs.getString("cat_breed");
			 String servicetoPerform = myRs.getString("service_to_perform");
			 Date admissionDate = myRs.getDate("admission_date");
			 // create admission object
			 Admission tempAdmission = new Admission(patientId,ownerFirstName,ownerLastName,ownerEmail,ownerContact,catName,catBreed,servicetoPerform,admissionDate);
			 // add it to the list of admission
			 patient.add(tempAdmission);
		 }
		
		
		}
		finally {
			// close jdbc object
			close(myConn,myStmt,myRs);
		}
		 
		return patient;
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try {
			if(myConn != null) {
				myConn.close();
			}
			if(myStmt != null) {
				myStmt.close();
			}
			if(myRs != null) {
				myRs.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
	public void addAdmission(Admission patient) throws Exception {


		// Create Object Connection
				Connection myConn = null;
				PreparedStatement myStmt = null;
				
				
				 try {
				// get a connection 
				myConn = dataSource.getConnection();
				System.out.print(myConn);
				// create sql statement
				String sql = "insert into admission " 
						+ "(owner_first_name,owner_last_name,owner_email,owner_contact,cat_name,cat_breed,service_to_perform,admission_date) "
						+ " VALUES (? , ? , ? ,? , ? , ? , ? ,? )";
				
				myStmt = myConn.prepareStatement(sql);
				// set param value
				myStmt.setString(1, patient.getOwnerFirstName());
				myStmt.setString(2, patient.getOwnerLastName());
				myStmt.setString(3, patient.getOwnerEmail());
				myStmt.setString(4, patient.getOwnerContact());
				myStmt.setString(5, patient.getCatName());
				myStmt.setString(6, patient.getCatBreed());
				myStmt.setString(7, patient.getServicetoPerform());
				System.out.println("The Date: " + patient.getAdmissionDate());
				myStmt.setDate(8, new java.sql.Date(patient.getAdmissionDate().getTime()));
				
				myStmt.execute();
				}
				finally {
					// close jdbc object
					close(myConn,myStmt,null);
				}
				 

	}
	
	
	private String modifyDateLayout(String inputDate) throws ParseException{
	    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").parse(inputDate);
	    return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date);
	}

	public Admission getAdmission(String thepatientId) throws Exception{
		Admission thePatient = null;
		
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		int patientId;
		
		
		try {
			// convert student id to int
			patientId = Integer.parseInt(thepatientId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from admission where patient_id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, patientId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result row
			if(myRs.next()) {
				 String ownerFirstName = myRs.getString("owner_first_name");
				 String ownerLastName = myRs.getString("owner_last_name");
				 String ownerEmail = myRs.getString("owner_email");
				 String ownerContact = myRs.getString("owner_contact");
				 String catName = myRs.getString("cat_name");
				 String catBreed = myRs.getString("cat_breed");
				 String servicetoPerform = myRs.getString("service_to_perform");
				 Date admissionDate = myRs.getDate("admission_date");
				 
				 
				 
				 //use the student id during construction
				 thePatient = new Admission(patientId,ownerFirstName,ownerLastName,ownerEmail,ownerContact,catName,catBreed,servicetoPerform,admissionDate);
				 return thePatient;
			}else {
				throw new Exception("Could not find Patient Id: " + patientId);
			}
			
			
		}
		finally {
			
		// clean up jdbc object
			close(myConn,myStmt,myRs);
		}
				
				
				

	}

	public void updateAdmission(Admission thePatient) throws Exception {
		
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		
		try {
		// get db connection 
		myConn = dataSource.getConnection();
		
		// create SQL update statement
		String sql = "update admission "
				+ " set owner_first_name=? , owner_last_name=? , owner_email=? , owner_contact=?"
				+ ", cat_name=?, cat_breed=? , service_to_perform=? , admission_date=? where patient_id=?";
		
		// prepare statement 
		
		myStmt = myConn.prepareStatement(sql);
		
		// set params
		myStmt.setString(1, thePatient.getOwnerFirstName());
		myStmt.setString(2, thePatient.getOwnerLastName());
		myStmt.setString(3, thePatient.getOwnerEmail());
		myStmt.setString(4, thePatient.getOwnerContact());
		myStmt.setString(5, thePatient.getCatName());
		myStmt.setString(6, thePatient.getCatBreed());
		myStmt.setString(7, thePatient.getServicetoPerform());
		myStmt.setDate(8, new java.sql.Date(thePatient.getAdmissionDate().getTime()));
		myStmt.setInt(9, thePatient.getPatientId());
		
		// execute SQL Statement
		myStmt.execute();
		}finally {
			// clean jdbc 
			close(myConn,myStmt,null);
		}
		
	}

	public List<Admission> searchAdmission(String theSearchName)  throws Exception {
        List<Admission> thePatients = new ArrayList<>();
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int studentId;
        
        try {
            
            // get connection to database
            myConn = dataSource.getConnection();
            
            //
            // only search by name if theSearchName is not empty
            //
            if (theSearchName != null && theSearchName.trim().length() > 0) {
                // create sql to search for students by name
                String sql = "select * from admission where lower(owner_first_name) like ? or lower(owner_last_name) like ?";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
                // set params
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
                myStmt.setString(2, theSearchNameLike);
                
            } else {
                // create sql to get all students
                String sql = "select * from admission order by patient_id";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
            }
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            while (myRs.next()) {
                
                // retrieve data from result set row
                int patientId = myRs.getInt("patient_id");
                String ownerFirstName = myRs.getString("owner_first_name");
				 String ownerLastName = myRs.getString("owner_last_name");
				 String ownerEmail = myRs.getString("owner_email");
				 String ownerContact = myRs.getString("owner_contact");
				 String catName = myRs.getString("cat_name");
				 String catBreed = myRs.getString("cat_breed");
				 String servicetoPerform = myRs.getString("service_to_perform");
				 Date admissionDate = myRs.getDate("admission_date");
				 //use the student id during construction
				 Admission thePatient = new Admission(patientId,ownerFirstName,ownerLastName,ownerEmail,ownerContact,catName,catBreed,servicetoPerform,admissionDate);
			
                
                // add it to the list of students
                thePatients.add(thePatient);            
            }
            
            return thePatients;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }
	
	
	public void deleteAdmission(String thepatientId)throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert admission id to int.
			int patientId = Integer.parseInt(thepatientId);
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete statement
			String sql = "DELETE FROM admission where patient_id=?";
			
			// prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, patientId);
			
			
			// execute sql statement
			myStmt.execute();
			
			
		}finally {
			//clean up JDBC object
			close (myConn,myStmt,null);
			
		}
	}


}
