<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit Book</title>
</head>
<body>
    <h1>Edit Book</h1>
    <form action="updateBook" method="post">
        <input type="hidden" name="bookId" value="${book.bookId}">
        Title: <input type="text" name="title" value="${book.title}" required><br>
        Author: <input type="text" name="author" value="${book.author}" required><br>
        Quantity: <input type="number" name="quantity" value="${book.quantity}" required><br>
        <input type="submit" value="Update Book">
    </form>
    <a href="view_books.jsp">Back to View Books</a>
</body>
</html>