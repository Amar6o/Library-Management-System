package com.library.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.DBDAO;
import com.library.dao.StudentDAO;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBDAO dbDao = new DBDAO();
		String idParameter = request.getParameter("student_id");
		if (idParameter == null || idParameter.isEmpty()) {
			// Redirect user to an error page or display an error message
			response.sendRedirect("errorPage.jsp?message=ID parameter is missing or empty.");
			return;
		}

		try {
			int studentId = Integer.parseInt(idParameter);
			dbDao.connect();
			StudentDAO studentDAO = new StudentDAO(dbDao);

			if (studentDAO.deleteStudent(studentId)) {
				response.sendRedirect("view_students.jsp");
			} else {
				response.sendRedirect("view_students.jsp?error=failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// Redirect user to an error page or display an error message for
			// invalid ID parameter
			response.sendRedirect("errorPage.jsp?message=Invalid Student ID parameter.");
		} finally {
			try {
				dbDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
