package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.bean.Book;

public class BookDAO {
	private DBDAO dbDao;

	public BookDAO(DBDAO dbDao) {
		this.dbDao = dbDao;
	}

	// Method to add a new book to the database
	public boolean addBook(Book book) throws SQLException {
		String sql = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";
		try (Connection con = dbDao.getDbCon(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, book.getTitle());
			pst.setString(2, book.getAuthor());
			pst.setInt(3, book.getQuantity());
			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		}
	}

	// Method to retrieve all books from the database
	public List<Book> getAllBooks() throws SQLException {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM books";
		try (Connection con = dbDao.getDbCon();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setQuantity(rs.getInt("quantity"));
				books.add(book);
			}
		}
		return books;
	}

	// Method to retrieve a book by its ID from the database
	public Book getBookById(int bookId) throws SQLException {
		String sql = "SELECT * FROM books WHERE book_id=?";
		try (Connection con = dbDao.getDbCon(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, bookId);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					Book book = new Book();
					book.setBookId(rs.getInt("book_id"));
					book.setTitle(rs.getString("title"));
					book.setAuthor(rs.getString("author"));
					book.setQuantity(rs.getInt("quantity"));
					return book;
				}
			}
		}
		return null;
	}

	// Method to update an existing book in the database
	public boolean updateBook(Book book) throws SQLException {
		String sql = "UPDATE books SET title=?, author=?, quantity=? WHERE book_id=?";
		try (Connection con = dbDao.getDbCon(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, book.getTitle());
			pst.setString(2, book.getAuthor());
			pst.setInt(3, book.getQuantity());
			pst.setInt(4, book.getBookId());
			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		}
	}

	// Method to delete a book from the database
	public boolean deleteBook(int bookId) throws SQLException {
		String sql = "DELETE FROM books WHERE book_id=?";
		try (Connection con = dbDao.getDbCon(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, bookId);
			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		}
	}

	public boolean decreaseQuantity(int bookId) throws SQLException {
		String sql = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ? AND quantity > 0";

		try (Connection con = dbDao.getDbCon(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, bookId);

			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		}
	}

	public boolean increaseQuantity(int bookId) throws SQLException {
		String sql = "UPDATE books SET quantity = quantity + 1 WHERE book_id = ?";

		try (Connection con = dbDao.getDbCon(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, bookId);
			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		}
	}

	public boolean checkAvailability(int bookId) throws SQLException {
		String sql = "SELECT quantity FROM books WHERE book_id = ?";
		try (Connection con = dbDao.getDbCon(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, bookId);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int quantity = rs.getInt("quantity");
					return quantity > 0; // Return true if quantity is greater
											// than 0
				} else {
					return false; // Book not found
				}
			}
		}
	}

}
