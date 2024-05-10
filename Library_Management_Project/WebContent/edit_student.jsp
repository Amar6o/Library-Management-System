<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Student</title>
</head>
<body>
    <h1>Edit Student</h1>
    <form action="updateStudent" method="post">
        <input type="hidden" name="studentId" value="${student.studentId}">
        Student Name: <input type="text" name="studentName" value="${student.studentName}"><br>
        Email: <input type="email" name="email" value="${student.email}"><br>
        Phone: <input type="text" name="phone" value="${student.phone}"><br>
        <input type="submit" value="Update Student">
    </form>
    <a href="view_students.jsp">Back to Students</a>
</body>
</html>
