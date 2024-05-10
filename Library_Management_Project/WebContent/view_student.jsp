<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.library.bean.Student"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Student Details</title>
</head>
<body>
	<h1>Student Details</h1>
	<p>
		<strong>Student ID:</strong> ${student.studentId}
	</p>
	<p>
		<strong>Student Name:</strong> ${student.studentName}
	</p>
	<p>
		<strong>Email:</strong> ${student.email}
	</p>
	<p>
		<strong>Phone:</strong> ${student.phone}
	</p>
	<a href="view_students.jsp">Back to Students</a>
</body>
</html>