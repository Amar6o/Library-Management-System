<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.library.bean.Book"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Book Details</title>
</head>
<body>
	<h1>Book Details</h1>
	<p>
		<strong>Book ID:</strong> ${book.bookId}
	</p>
	<p>
		<strong>Title:</strong> ${book.title}
	</p>
	<p>
		<strong>Author:</strong> ${book.author}
	</p>
	<p>
		<strong>Quantity:</strong> ${book.quantity}
	</p>
	<a href="view_books.jsp">Back to Books</a>
</body>
</html>
