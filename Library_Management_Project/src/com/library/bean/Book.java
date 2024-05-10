package com.library.bean;

public class Book {
	private int bookId;
	private String title;
	private String author;
	private int quantity;

	// Constructors
	public Book() {
	}

	public Book(int bookId, String title, String author, int quantity) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.quantity = quantity;
	}

	// Getters and Setters
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// toString method for representing the Book object as a string
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", quantity=" + quantity + "]";
	}
}
