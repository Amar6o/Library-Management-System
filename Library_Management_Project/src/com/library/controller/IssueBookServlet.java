package com.library.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.BookDAO;
import com.library.dao.DBDAO;
import com.library.dao.TransactionDAO;

@WebServlet("/issueBook")
public class IssueBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int studentId = Integer.parseInt(request.getParameter("studentId"));

		// Declaring and initializing the DBDAO object
		DBDAO dbDao = new DBDAO();

		try {
			// Establishing database connection
			dbDao.connect();

			// Creating instances of DAO classes
			BookDAO bookDAO = new BookDAO(dbDao);
			TransactionDAO transactionDAO = new TransactionDAO(dbDao);

			if (bookDAO.checkAvailability(bookId)) {
				if (transactionDAO.issueBook(studentId, bookId) && bookDAO.decreaseQuantity(bookId)) {
					response.sendRedirect("success.jsp?message=Book issued successfully.");
					return;
				}
			}

			response.sendRedirect("error.jsp?message=Failed to issue book. Please try again.");
		} catch (NumberFormatException e) {
			response.sendRedirect("error.jsp?message=Invalid input.");
		} catch (SQLException e) {
			// Log the exception
			e.printStackTrace();
			response.sendRedirect("error.jsp?message=An unexpected database error occurred.");
		} catch (ClassNotFoundException e) {
			// Log the exception
			e.printStackTrace();
			response.sendRedirect("error.jsp?message=Database driver not found.");
		} finally {
			// Closing the database connection
			try {
				dbDao.close();
			} catch (SQLException e) {
				// Log the exception
				e.printStackTrace();
			}
		}
	}
}
