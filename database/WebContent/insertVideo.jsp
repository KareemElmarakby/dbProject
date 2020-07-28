<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*,java.sql.* " %>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
  border: 1px solid black;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert Videos</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/logvideo" name="test2" method="post">
   <table style="with: 80%">
    <tr>
     <td>User</td>
     <td><input value=<% session.getAttribute("email"); %> name="email" /> <% System.out.println("email"); %></td>
    </tr>
    <tr>
     <td>URL for YouTube Video</td>
     <td><input type="text" name="url" /></td>
    </tr>
    <tr>
     <td>Video Title</td>
     <td><input type="text" name="title" /></td>
    </tr>
    <tr>
     <td>Description</td>
     <td><input type="text" name="desc" /></td>
    </tr>
    <tr>
    <tr>
     <td>Tags (One word each, separated by comma)</td>
     <td><input type="text" name="tags" /></td>
    </tr>
    <tr>
     <td>Comedian (First Name)</td>
	 <td><input type="text" name="comFirst" /></td>
    </tr>
    <tr>
     <td>Comedian (Last Name)</td>
	 <td><input type="text" name="comLast" /></td>
    </tr>
    <tr>
     <td>Comedian (Birthday [in YYYY-MM-DD format])</td>
	 <td><input type="text" name="comDate" /></td>
    </tr>
    <tr>
     <td>Comedian (Birthplace)</td>
	 <td><input type="text" name="comPlace" /></td>
    </tr>
   </table>
   <input type="submit" value="Submit" />
  </form>
</body>
</html>