<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Standard User Home Page</title>
</head>
 <%
            String email = request.getParameter("user");
            session.setAttribute("email", email);
        %> 
<body>
		<a href="search.jsp">Click here to search the database for a video</a>
		<a href="insertVideo.jsp">Click here to insert video into database</a>
		<a href="login.jsp">Click here to go back to login</a>
</body>
</html>