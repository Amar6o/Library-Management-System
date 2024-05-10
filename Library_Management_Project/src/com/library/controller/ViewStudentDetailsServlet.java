package com.library.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.Student;
import com.library.dao.DBDAO;
import com.library.dao.StudentDAO;

@WebServlet("/viewStudent")
public class ViewStudentDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParameter = request.getParameter("student_id");

		if (idParameter == null || idParameter.isEmpty()) {
			// Redirect user to an error page or display an error message
			response.sendRedirect("errorPage.jsp?message=ID parameter is missing or empty.");
			return;
		}

		DBDAO dbDao = new DBDAO();
		try {
			int id = Integer.parseInt(idParameter);
			dbDao.connect();
			StudentDAO studentDAO = new StudentDAO(dbDao);

			Student student = studentDAO.getStudentById(id);

			if (student != null) {
				// Set student object as an attribute in the request
				request.setAttribute("student", student);
				// Forward the request to the JSP page for viewing student
				// details
				request.getRequestDispatcher("view_student.jsp").forward(request, response);
			} else {
				// Redirect user to an error page or display an error message
				response.sendRedirect("errorPage.jsp?message=Student with ID " + id + " not found.");
			}
		} catch (NumberFormatException e) {
			// Redirect user to an error page or display an error message for
			// invalid ID parameter
			response.sendRedirect("errorPage.jsp?message=Invalid ID parameter.");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace(); // Log the exception for debugging
			// Redirect user to an error page or display an error message for
			// unexpected errors
			response.sendRedirect("errorPage.jsp?message=An unexpected error occurred.");
		} finally {
			try {
				dbDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
