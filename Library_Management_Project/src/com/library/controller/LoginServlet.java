package com.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.LoginDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get username and password from the login form
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Validate username and password using LoginDao
		LoginDao loginDao = new LoginDao("jdbc:mysql://localhost:3306/librarydb", "root", "amar@2627");
		boolean isValidUser = loginDao.validateUser(username, password);

		// Redirect the user based on the validation result
		if (isValidUser) {
			// If valid, redirect to a welcome page (you can change the URL to
			// your desired page)
			response.sendRedirect("welcome.jsp");
		} else {
			// If invalid, redirect back to the login page with an error message
			response.sendRedirect("login.jsp?error=invalid");
		}
	}
}
