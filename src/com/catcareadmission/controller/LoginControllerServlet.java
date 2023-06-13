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

import com.catcareadmission.model.*;
import java.util.*;
import com.catcareadmission.Services.*;
/**
 * Servlet implementation class LoginControllerServlet
 */
@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginDBUtil login;
    
	@Resource(name="jdbc/web_cat")
	private DataSource dataSource;
    
    public void init() {
    	login = new LoginDBUtil();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    	
    	// list the admission ... mvc way 
    			try {
    				// read the command parameter
    				String theCommand = request.getParameter("command");
    				
    				if(theCommand == null) {
    					theCommand = "LOGIN";
    				}
    				// route to the appropriate method
    				switch(theCommand) {
    				
    				case "LOGIN" :
    					loginToAdmission(request,response);
    					break;
    				
    				default:
    					  HttpSession session = request.getSession();
    		                //session.setAttribute("user", username);
    		             // send to jsp page: update-admission-form.jsp
    		        		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
    		        		dispatcher.forward(request, response);
    				}
    				
    				
    			} catch (Exception e) {
    				
    				throw new ServletException(e);
    			}
    			
    
    }

	private void loginToAdmission(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
	{
	
    	
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Login loginBean = new Login(username,password);
       

        try {
          
                //HttpSession session = request.getSession();
                // session.setAttribute("username",username);
            	RequestDispatcher dispatcher = request.getRequestDispatcher("AdmissionControllerServlet");
        		dispatcher.forward(request, response);
            
        } catch (Exception e) {
        	
            e.printStackTrace();
        }
		
	}
}