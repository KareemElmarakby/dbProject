<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body> 


 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Search For Video</title>

 </head>
  <%
            String email = request.getParameter("user");
            session.setAttribute("email", email);
        %> 
<form action="<%= request.getContextPath() %>/searchvideo" name="test2" method="post">
   <table style="with: 80%">
    <tr>
     <td>Search</td>
     <td><input type="text" name="search" /></td>
    </tr>
    </table>
   <input type="submit" value="Submit" />
  </form>
    <%
        String x = (String) application.getAttribute("id");
    //out.println(x);
    %>
