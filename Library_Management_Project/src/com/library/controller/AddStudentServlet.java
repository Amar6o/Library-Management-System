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

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String studentName = request.getParameter("studentName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		Student student = new Student(studentName, email, phone);
		DBDAO dbDao = new DBDAO();
		try {
			dbDao.connect();
			StudentDAO studentDAO = new StudentDAO(dbDao);

			if (studentDAO.addStudent(student)) {
				response.sendRedirect("view_students.jsp");
			} else {
				response.sendRedirect("add_student.jsp?error=failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("errorPage.jsp?message=An unexpected database error occurred.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			response.sendRedirect("errorPage.jsp?message=Database driver not found.");
		} finally {
			try {
				dbDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
