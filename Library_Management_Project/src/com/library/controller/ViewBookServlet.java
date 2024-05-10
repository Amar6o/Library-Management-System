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

@WebServlet("/viewBook")
public class ViewBookServlet extends HttpServlet {
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
			int bookId = Integer.parseInt(idParameter);
			dbDao.connect();
			BookDAO bookDAO = new BookDAO(dbDao);

			Book book = bookDAO.getBookById(bookId);

			if (book != null) {
				// Set the book object as an attribute in the request
				request.setAttribute("book", book);

				// Forward the request to the JSP page for viewing book details
				request.getRequestDispatcher("view_book.jsp").forward(request, response);
			} else {
				// Redirect to an error page or display an error message
				response.sendRedirect("errorPage.jsp?message=Book with ID " + bookId + " not found.");
			}
		} catch (NumberFormatException e) {
			// Redirect to an error page or display an error message for invalid
			// book ID
			response.sendRedirect("errorPage.jsp?message=Invalid Book ID parameter.");
		} catch (SQLException | ClassNotFoundException e) {
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
