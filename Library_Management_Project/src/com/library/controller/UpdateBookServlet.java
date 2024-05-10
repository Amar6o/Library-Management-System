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

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		Book book = new Book();
		book.setBookId(bookId);
		book.setTitle(title);
		book.setAuthor(author);
		book.setQuantity(quantity);

		// Declare the DBDAO and BookDAO outside try-catch-finally block
		DBDAO dbDao = null;
		try {
			dbDao = new DBDAO();
			dbDao.connect();
			BookDAO bookDAO = new BookDAO(dbDao);
			if (bookDAO.updateBook(book)) {
				response.sendRedirect("view_books.jsp");
			} else {
				response.sendRedirect("edit_book.jsp?id=" + bookId + "&error=failed");
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Log the exception
			response.sendRedirect("errorPage.jsp?message=An unexpected error occurred.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // Log the exception
			response.sendRedirect("errorPage.jsp?message=An unexpected error occurred.");
		} finally {
			// Close the connection in the finally block
			if (dbDao != null) {
				try {
					dbDao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
