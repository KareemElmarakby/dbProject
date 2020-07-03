<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User Already Exists</title>
</head>
<body>
    <h1>Using GET Method to Read Form Data</h1>
    <ul>
        <li><p>
                <b>Username:</b>
                <%=request.getParameter("user")%>
            </p></li>
        <li><p>
                <b>Password:</b>
                <%=request.getParameter("pass")%>
            </p></li>
    </ul>
</body>