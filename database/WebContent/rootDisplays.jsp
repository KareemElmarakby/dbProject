<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
  border: 1px solid black;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/displaytrends" name="test2" method="post">
   <table style="with: 80%">
    <tr>
     <td>Who's Cool</td>
     <td><input type="submit" value="cool" name="Submit" /></td>
    </tr>
    <tr>
     <td>Who's New</td>
     <td><input type="submit" value="new" name="Submit" /></td>
    </tr>
    <tr>
     <td>Who's Hot</td>
     <td><input type="submit" value="hot" name="Submit" /></td>
    </tr>
    <tr>
     <td>Who's Top</td>
     <td><input type="submit" value="top" name="Submit" /></td>
    </tr>
    <tr>
     <td>Popular Tags</td>
     <td><input type="submit" value="tags" name="Submit" /></td>
    </tr>
    <tr>
     <td>Common Favorite Comedians</td>
     <td><input type="submit" value="favorite" name="Submit" /></td>
     <%Class.forName("com.mysql.jdbc.Driver"); 
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?"
	              + "user=root&password=pass123"); 
       Statement st1 = con.createStatement(); 
       ResultSet rs1 = st1.executeQuery("select email from Users");
       Statement st2 = con.createStatement();
       ResultSet rs2 = st2.executeQuery("select email from Users");%>
	 <td>
    	<select name="user1">
        <%  while(rs1.next()){ %>
            <option value=<%=rs1.getString(1)%>><%= rs1.getString(1)%></option>
        <% } %>
        </select>
	 </td>
	 <td>
    	<select name="user2">
        <%  while(rs2.next()){ %>
            <option value=<%=rs2.getString(1)%>><%= rs2.getString(1)%></option>
        <% } %>
        </select>
	 </td>
    </tr>
    <tr>
     <td>Most Productive Users</td>
     <td><input type="submit" value="productive" name="Submit" /></td>
    </tr>
    <tr>
     <td>Positive Reviewers</td>
     <td><input type="submit" value="reviewers" name="Submit" /></td>
    </tr>
    <tr>
     <td>Poor Youtubes</td>
     <td><input type="submit" value="poor" name="Submit" /></td>
    </tr>
    <tr>
     <td>Twin Users</td>
     <td><input type="submit" value="twins" name="Submit" /></td>
    </tr>
    </table>
  </form>
  <a href='RootHomePage.jsp'>Back</a>
</body>
</html>