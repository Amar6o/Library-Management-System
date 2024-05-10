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

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setQuantity(quantity);

		DBDAO dbDao = new DBDAO();
		try {
			dbDao.connect();
			BookDAO bookDAO = new BookDAO(dbDao);
			bookDAO.addBook(book);
			response.sendRedirect("view_books.jsp");
		} catch (SQLException e) {
			e.printStackTrace(); // Log the exception
			response.sendRedirect("errorPage.jsp?message=An unexpected error occurred.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // Log the exception
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