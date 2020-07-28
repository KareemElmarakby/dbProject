<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Standard User Home Page</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/userrequest" name="userhome" method="post">
		<h2>Click here to search the database for a video</h2>
		<input type="submit" value="Search" name="menu" />
		<br>
		<h2>Click here to add/remove a comedian to your favorites!</h2>
		<input type="submit" value="Favorites" name="menu" />
		<br>
		<h2>Click here to insert video into database</h2>
		<input type="submit" value="Insert" name="menu" />
		<br>
		<h2>Click here to go back to login</h2>
		<input type="submit" value="Login" name="menu" />
		<input type="hidden" name="email" value=<%= request.getAttribute("email")%>>
</form>
</body>
</html>