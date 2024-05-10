<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Issue Book</title>
</head>
<body>
    <h1>Issue Book</h1>
    <form action="issueBook" method="post">
        <label for="studentId">Student ID:</label>
        <input type="text" id="studentId" name="studentId" required><br>
        
        <label for="bookId">Book ID:</label>
        <input type="text" id="bookId" name="bookId" required><br>
        
        <input type="submit" value="Issue Book">
    </form>
</body>
</html>