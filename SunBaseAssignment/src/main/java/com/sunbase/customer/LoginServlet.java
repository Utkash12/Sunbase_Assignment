package com.sunbase.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
//Its the servlet that use to handle the login functionality which is  mapped to /loginServlet URL
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	
    // Its a Unique identifier for this servlet version, which is mainly used for serialization
    private static final long serialVersionUID = 1L;

    //Its a handle POST requests, used for submitting the forms
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	response.sendRedirect("customer?action=list");
    	//Commenting the below logic as per the requirements of the assignment 
		/*
		 * //It helps in retrieving the username and password submitted by the login
		 * form String username = request.getParameter("username"); String password =
		 * request.getParameter("password");
		 * 
		 * //Here is the authentication check for the credentials where username must be
		 * admin and password must be password if ("utkarsh".equals(username) &&
		 * "utkarsh@123".equals(password)) { // If the credentials are correct, it will be
		 * redirecting to the customer listing page
		 * response.sendRedirect("customer?action=list"); } else { // If the credentials
		 * are not correct, it will be redirecting back to the login page with an error
		 * parameter response.sendRedirect("index.jsp?error=1"); }
		 */
    }
}
