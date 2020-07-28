<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/updateFavCom" name="test2" method="post">
   <table style="with: 80%">
    <tr>
     <td>Add A Comedian To Your List</td>
     <td><input type="submit" value="Add" name="Submit" /></td>
     <%Class.forName("com.mysql.jdbc.Driver"); 
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?"
	              + "user=root&password=pass123"); 
       Statement st1 = con.createStatement(); 
       ResultSet rs1 = st1.executeQuery("select comid, firstname, lastname from Comedians");
       Statement st2 = con.createStatement();
       ResultSet rs2 = st2.executeQuery("select comid, firstname, lastname from Comedians");%>
	 <td>
    	<select name="addCom">
        <%  while(rs1.next()){ %>
            <option value=<%=rs1.getInt(1)%>><%= rs1.getString(2)%> <%= rs1.getString(3)%></option>
        <% } %>
        </select>
	 </td>
    </tr>
    <tr>
     <td>Delete A Comedian From Your List</td>
     <td><input type="submit" value="Delete" name="Submit" /></td>
	 <td>
    	<select name="removeCom">
        <%  while(rs2.next()){ %>
            <option value=<%=rs2.getInt(1)%>><%= rs2.getString(2)%> <%= rs2.getString(3)%></option>
        <% } %>
        </select>
	 </td>
    </tr>
    </table>
    <input type="submit" value="Submit" />
  </form>
</body>
</html>