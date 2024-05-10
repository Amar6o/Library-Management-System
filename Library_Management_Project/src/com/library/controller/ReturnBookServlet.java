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

@WebServlet("/returnBook")
public class ReturnBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));

		DBDAO dbDao = new DBDAO();
		try {
			dbDao.connect();
			TransactionDAO transactionDAO = new TransactionDAO(dbDao);
			BookDAO bookDAO = new BookDAO(dbDao);

			// Check if the book was successfully returned
			if (transactionDAO.returnBook(studentId, bookId) && bookDAO.increaseQuantity(bookId)) {
				response.sendRedirect("success.jsp?message=Book returned successfully.");
				return; // Return after successful redirect
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp?message=Invalid input.");
			return; // Return after redirect
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp?message=An unexpected database error occurred.");
			return; // Return after redirect
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp?message=Database driver not found.");
			return; // Return after redirect
		} finally {
			try {
				dbDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// If execution reaches here, it means an error occurred
		response.sendRedirect("error.jsp?message=Failed to return book. Please try again.");
	}
}
