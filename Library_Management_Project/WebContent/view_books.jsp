<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.library.bean.Book"%>
<%@ page import="com.library.dao.BookDAO"%>
<%@ page import="com.library.dao.DBDAO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Books</title>
</head>
<body>
	<h1>View Books</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Book ID</th>
				<th>Title</th>
				<th>Author</th>
				<th>Quantity</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<%
				DBDAO dbDao = new DBDAO();
				try {
					dbDao.connect();
					BookDAO bookDAO = new BookDAO(dbDao);

					List<Book> books = bookDAO.getAllBooks();

					for (Book book : books) {
			%>
			<tr>
				<td><%=book.getBookId()%></td>
				<td><%=book.getTitle()%></td>
				<td><%=book.getAuthor()%></td>
				<td><%=book.getQuantity()%></td>
				<td><a href="viewBook?book_id=<%=book.getBookId()%>">View</a>
					<a href="editBook?book_id=<%=book.getBookId()%>">Edit</a> <a
					href="deleteBook?book_id=<%=book.getBookId()%>">Delete</a></td>
			</tr>
			<%
				}
				} catch (Exception e) {
					e.printStackTrace();
					out.println("Error retrieving books: " + e.getMessage());
				} finally {
					dbDao.close();
				}
			%>
		</tbody>
	</table>
	<a href="add_book.jsp">Add Book</a>
	<br>
	<a href="welcome.jsp">Back to Welcome Page</a>
</body>
</html>
