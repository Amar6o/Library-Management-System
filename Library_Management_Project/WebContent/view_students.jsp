<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.library.bean.Student"%>
<%@ page import="com.library.dao.StudentDAO"%>
<%@ page import="com.library.dao.DBDAO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Students</title>
</head>
<body>
	<h1>View Students</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Student ID</th>
				<th>Student Name</th>
				<th>Email</th>
				<th>Phone</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<%
				DBDAO dbDao = new DBDAO();
				try {
					dbDao.connect();
					StudentDAO studentDAO = new StudentDAO(dbDao);

					List<Student> students = studentDAO.getAllStudents();

					for (Student student : students) {
			%>
			<tr>
				<td><%=student.getStudentId()%></td>
				<td><%=student.getStudentName()%></td>
				<td><%=student.getEmail()%></td>
				<td><%=student.getPhone()%></td>
				<td><a
					href="viewStudent?student_id=<%=student.getStudentId()%>">View</a>
					<a href="editStudent?student_id=<%=student.getStudentId()%>">Edit</a>
					<a href="deleteStudent?student_id=<%=student.getStudentId()%>">Delete</a>
				</td>
			</tr>
			<%
				}
				} catch (Exception e) {
					e.printStackTrace();
					out.println("Error retrieving students: " + e.getMessage());
				} finally {
					dbDao.close();
				}
			%>
		</tbody>
	</table>
	<a href="add_student.jsp">Add Student</a>
	<br>
	<a href="welcome.jsp">Back to Welcome Page</a>
</body>
</html>