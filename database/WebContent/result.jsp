<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>




<!DOCTYPE html>
<html>
<body> 

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Videos</title>
    </head>
 <%
            String email = request.getParameter("user");
            session.setAttribute("email", email);

        %> 
    <center>    
        <table>
            <th>URLs</th>
			<th>Title</th>
			<th>Rating</th>
			<th>Comments</th>

        </table>

        <br>
        <br><a href='search.jsp'>Back</a>
</body> 
</html>