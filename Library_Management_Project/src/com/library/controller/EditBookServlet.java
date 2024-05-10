package com.library.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.Book;
import com.library.dao.BookDAO;
import com.library.dao.DBDAO;

@WebServlet("/editBook")
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParameter = request.getParameter("book_id");

		if (idParameter == null || idParameter.isEmpty()) {
			// Redirect user to an error page or display an error message
			response.sendRedirect("errorPage.jsp?message=ID parameter is missing or empty.");
			return;
		}

		DBDAO dbDao = new DBDAO();
		try {
			int id = Integer.parseInt(idParameter);
			dbDao.connect();
			BookDAO bookDAO = new BookDAO(dbDao);

			Book book = bookDAO.getBookById(id);

			if (book != null) {
				// Set the book object as an attribute in the request
				request.setAttribute("book", book);

				// Forward the request to the JSP page for editing book details
				request.getRequestDispatcher("edit_book.jsp").forward(request, response);
			} else {
				// Redirect to an error page or display an error message
				response.sendRedirect("errorPage.jsp?message=Book with ID " + id + " not found.");
			}
		} catch (NumberFormatException e) {
			// Redirect to an error page or display an error message for invalid
			// book ID
			response.sendRedirect("errorPage.jsp?message=Invalid Book ID parameter.");
		} catch (SQLException e) {
			e.printStackTrace(); // Log the exception for debugging
			// Redirect to an error page or display an error message for
			// database error
			response.sendRedirect("errorPage.jsp?message=An unexpected error occurred.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // Log the exception for debugging
			// Redirect to an error page or display an error message for
			// database error
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
