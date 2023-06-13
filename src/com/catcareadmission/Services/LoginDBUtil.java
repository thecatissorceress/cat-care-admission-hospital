package com.catcareadmission.Services;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import javax.sql.DataSource;

import com.catcareadmission.model.*;


import com.catcareadmission.model.Login;

public class LoginDBUtil {
	
	private DataSource dataSource;
	
	
	
	
	public LoginDBUtil() {
		
	}

	public LoginDBUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try {
			if(myRs != null) {
				myRs.close();
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
	
	
	
	public boolean validate(Login login) throws ClassNotFoundException , SQLException{
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		boolean status = false;
		
		try {
		// Step 2:Create a statement using connection object
			
			 myConn = dataSource.getConnection(); 
        String sql = "select * from login where username = ? and password = ? " ;
		myStmt = myConn.prepareStatement(sql);
        myStmt.setString(1, login.getUsername());
        myStmt.setString(2, login.getPassword());

     // execute statement
     	myRs = myStmt.executeQuery();
     	status = myRs.next();

    } catch (SQLException e) {
       
        printSQLException(e);
    }finally {
		// close jdbc object
		close(myConn,myStmt,myRs);
	}
		return status;
	}

private void printSQLException(SQLException ex) {

    for (Throwable e: ex) {
        if (e instanceof SQLException) {
            e.printStackTrace(System.err);
            System.err.println("SQLState: " + ((SQLException) e).getSQLState());
            System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
            System.err.println("Message: " + e.getMessage());
            Throwable t = ex.getCause();
            while (t != null) {
                System.out.println("Cause: " + t);
                t = t.getCause();
            }
        }
    }
}
}
