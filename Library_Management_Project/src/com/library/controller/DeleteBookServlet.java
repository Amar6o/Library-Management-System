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

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
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

			if (bookDAO.deleteBook(bookId)) {
				response.sendRedirect("view_books.jsp");
			} else {
				response.sendRedirect("view_books.jsp?error=failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// Redirect user to an error page or display an error message for
			// invalid ID parameter
			response.sendRedirect("errorPage.jsp?message=Invalid Book ID parameter.");
		} finally {
			try {
				dbDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}