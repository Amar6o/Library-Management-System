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

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String studentIdParam = request.getParameter("studentId");
		String studentName = request.getParameter("studentName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		// Check if the studentIdParam is empty or null
		if (studentIdParam == null || studentIdParam.isEmpty()) {
			// Redirect to an error page or display an error message
			response.sendRedirect("errorPage.jsp?message=Student ID parameter is missing or empty.");
			return;
		}

		// Parse the studentIdParam to an integer
		int studentId;
		try {
			studentId = Integer.parseInt(studentIdParam);
		} catch (NumberFormatException e) {
			// Redirect to an error page or display an error message for invalid
			// student ID
			response.sendRedirect("errorPage.jsp?message=Invalid Student ID parameter.");
			return;
		}

		Student student = new Student(studentId, studentName, email, phone);
		DBDAO dbDao = new DBDAO();
		try {
			dbDao.connect();
			StudentDAO studentDAO = new StudentDAO(dbDao);

			if (studentDAO.updateStudent(student)) {
				response.sendRedirect("view_students.jsp");
			} else {
				response.sendRedirect("edit_student.jsp?student_id=" + studentId + "&error=failed");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			// Redirect to error page or display an error message for database
			// error
			response.sendRedirect("errorPage.jsp?message=An unexpected error occurred.");
		} finally {
			// Ensure database connection is closed in the finally block
			try {
				if (dbDao != null) {
					dbDao.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
