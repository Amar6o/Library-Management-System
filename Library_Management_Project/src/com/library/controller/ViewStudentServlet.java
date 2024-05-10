package com.library.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.Student;
import com.library.dao.DBDAO;
import com.library.dao.StudentDAO;

@WebServlet("/viewStudents")
public class ViewStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBDAO dbDao = new DBDAO();
		try {
			dbDao.connect();
			StudentDAO studentDAO = new StudentDAO(dbDao);

			List<Student> students = studentDAO.getAllStudents();

			// Set the list of students as an attribute in the request
			request.setAttribute("students", students);
			// Forward the request to the JSP page for viewing students
			request.getRequestDispatcher("view_students.jsp").forward(request, response);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace(); // Log the exception for debugging
			// Redirect to an error page or display an error message for
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
