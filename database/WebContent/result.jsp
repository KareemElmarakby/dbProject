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
 
        <table>
        <tr>
            <th>URLs</th>
			<th>Title</th>
			<th>Rating</th>
			<th>Comments</th>
			<th>Submit</th>
		</tr>
		<% SearchDao.showResults(request.getParameter("search")); %>
        </table>

        <br>
        <br><a href='search.jsp'>Back</a>
</body> 
</html>