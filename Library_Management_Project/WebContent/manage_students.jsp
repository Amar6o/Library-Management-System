<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Student</title>
</head>
<body>
	<h1>Welcome Student</h1>
	<ul>
		<li><a href="view_students.jsp">View Students</a></li>
		<li><a href="add_student.jsp">Add Student</a></li>
		<!-- Add more links to other student management functionalities as needed -->
	</ul>
	<form action="logout" method="post">
		<input type="submit" value="Logout">
	</form>
</body>
</html>