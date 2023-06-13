package com.catcareadmission.controller;

import java.io.IOException;


import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.catcareadmission.Services.AdmissionDBUtil;
import com.catcareadmission.Services.LoginDBUtil;
import com.catcareadmission.model.*;
import java.util.*;

/**
 * Servlet implementation class AdmissionControllerServlet
 */
@WebServlet("/AdmissionControllerServlet")
public class AdmissionControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AdmissionDBUtil admissionDBUtil;
	private LoginDBUtil login;
	@Resource(name="jdbc/web_cat")
	private DataSource dataSource;
	
	
	
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		
		// create our admissions db util ... and pass in the conn pool / datasource
		try {
			admissionDBUtil = new AdmissionDBUtil(dataSource);
			login = new LoginDBUtil(dataSource);
		}
		catch(Exception exc) {
			throw new ServletException(exc);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
	
		 try {
	            // read the "command" parameter
	            String theCommand = request.getParameter("command");
	                    
	            // route to the appropriate method
	            switch (theCommand) {
	                            
	            case "ADD":
	                addAdmission(request, response);
	                break;
	            case "LOGIN":
	            	loginToAdmission(request,response);
	            	break;
	            default:
	                listAdmission(request, response);
	                break;
	            }
	                
	        }
	        catch (Exception exc) {
	            throw new ServletException(exc);
	        }
		
	}



		private void loginToAdmission(HttpServletRequest request, HttpServletResponse response)
				 throws ServletException, IOException
		{
		
	    	
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        Login loginBean = new Login(username,password);
	       

	        try {
	        	if(login.validate(loginBean)) {
	        		System.out.print(login.validate(loginBean));
	                HttpSession session = request.getSession();
	                 session.setAttribute("username",username);
	        		//send them back to list admission page
					 listAdmission(request,response);
					 
	        	}
	        	else {
	        		HttpSession session = request.getSession();
	                session.setAttribute("user", username);
	                response.sendRedirect("index.jsp");
	        	}
	            
	        } catch (Exception e) {
	        	
	            e.printStackTrace();
	        }
			
		}
		
	


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// list the admission ... mvc way 
		try {
			// read the command parameter
			String theCommand = request.getParameter("command");
			
			if(theCommand == null) {
				theCommand = "LIST";
			}
			// route to the appropriate method
			switch(theCommand) {
			
			case "LIST" :
				listAdmission(request,response);
				break;
			case "ADD" : 
				addAdmission(request,response);
				break;
			case "LOAD":
				loadAdmission(request,response);
				break;
			case "UPDATE":
				updateAdmission(request,response);
				break;
			case "DELETE":
				deleteAdmission(request,response);
				break;
			case "SEARCH":
                searchAdmission(request, response);
                break;
			default:
				listAdmission(request,response);
				
			}
			
			
		} catch (Exception e) {
			
			throw new ServletException(e);
		}
		
	}



	private void searchAdmission(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read search name from form data
        String theSearchName = request.getParameter("theSearchName");
        
        // search students from db util
        List<Admission> students = admissionDBUtil.searchAdmission(theSearchName);
        
        // add students to the request
        request.setAttribute("ADMISSION_LIST", students);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-admission.jsp");
        dispatcher.forward(request, response);
    }

	private void deleteAdmission(HttpServletRequest request, HttpServletResponse response)throws Exception {
		// read patient id from the form data
				String patientId = request.getParameter("patientId");
				
				// get admission from database
				 admissionDBUtil.deleteAdmission(patientId);


				//send them back to list admission page
				 listAdmission(request,response);
	}





	private void updateAdmission(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// read the patient id from the form data
		int id = Integer.parseInt(request.getParameter("patientId"));
		
		String ownerFirstName = request.getParameter("ownerFirstName");
		String ownerLastName = request.getParameter("ownerLastName");
		String ownerEmail = request.getParameter("ownerEmail");
		String ownerContact = request.getParameter("ownerContact");
		String catName = request.getParameter("catName");
		String catBreed = request.getParameter("catBreed");
		String serviceToPerform = request.getParameter("serviceToPerform");
		String formatDate = request.getParameter("admissionDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date admissionDate = sdf.parse(formatDate);
		//create a new student object
		Admission patient = new Admission(id,ownerFirstName,ownerLastName,ownerEmail,ownerContact,catName,catBreed,serviceToPerform,admissionDate);
		
		// perform update on the database
		admissionDBUtil.updateAdmission(patient);
		
		// send them back to the "list admission" page
		listAdmission(request,response);
		
	}





	private void loadAdmission(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read patient id from the form data
		String patientId = request.getParameter("patientId");
		
		// get admission from database
		Admission thePatient = admissionDBUtil.getAdmission(patientId);


		// place the admission in the request attribute
		request.setAttribute("THE_PATIENT", thePatient);


		// send to jsp page: update-admission-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-admission-form.jsp");
		dispatcher.forward(request, response);
	}
	
	





	private void addAdmission(HttpServletRequest request, HttpServletResponse response) throws Exception, ParseException {
		
		// read student info from form data
		String ownerFirstName = request.getParameter("ownerFirstName");
		String ownerLastName = request.getParameter("ownerLastName");
		String ownerEmail = request.getParameter("ownerEmail");
		String ownerContact = request.getParameter("ownerContact");
		String catName = request.getParameter("catName");
		String catBreed = request.getParameter("catBreed");
		String serviceToPerform = request.getParameter("serviceToPerform");
		String formatDate = request.getParameter("admissionDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date admissionDate = sdf.parse(formatDate);
		//create a new student object
		Admission patient = new Admission(ownerFirstName,ownerLastName,ownerEmail,ownerContact,catName,catBreed,serviceToPerform,admissionDate);
		
		// add the student to the database
		admissionDBUtil.addAdmission(patient);
		// send back to main pages
		 response.sendRedirect(request.getContextPath() + "/AdmissionControllerServlet?command=LIST");
		
		
		
	}





	private void listAdmission(HttpServletRequest request, HttpServletResponse response) 

	throws Exception
	{
		
		// get admission from db util
		System.out.println(admissionDBUtil.getAdmission());
		List<Admission> patients =  admissionDBUtil.getAdmission();
		
		// add students to the request
		request.setAttribute("ADMISSION_LIST", patients);
		
		// send to jsp page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-admission.jsp");
		dispatcher.forward(request, response);
	}

	

}
