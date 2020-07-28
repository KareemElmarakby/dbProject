<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>

<!DOCTYPE html>
<html>
<body> 
<%
System.out.println("email is " + session.getAttribute("email")); 
%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Videos</title>
    </head>
 		<form action="<%= request.getContextPath() %>/logreview" name="review" method="post">
        <table>
        <tr>
            <th>URLs</th>
			<th>Title</th>
			<th>Rating</th>
			<th>Comments</th>
			<th>Submit</th>
		</tr>
		<% try {
	    	Class.forName("com.mysql.jdbc.Driver");
	        System.out.println("Select a table and then print out its content.");
	        Connection connect = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/testdb?"
	                + "user=root&password=pass123");
        
			Statement st = connect.createStatement();
			ResultSet rs;
			String searching = request.getParameter("search");

			PreparedStatement ps = connect.prepareStatement("SELECT YT.url, YT.title FROM youtubevideos YT, Comedians C, VideoTags VT " + 
					 "WHERE (YT.comid = C.comid) AND (YT.url = VT.url) AND (C.firstname=? OR C.lastname=? OR VT.tag=?) " +
					 "GROUP BY YT.url");
			ps.setString(1, searching);
			ps.setString(2, searching);
			ps.setString(3, searching);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				
				String url = rs.getString(1);
				String title = rs.getString(2); %>
				
				<tr>
				<td><input type="hidden" name="url" value=<%=url%> /><%= url %></td>
				<td><%= title %></td>
				<td><select name='rating'><option value='p'>P</option><option value='f'>F</option><option value='g'>G</option><option value='E'>E</option></select></td>
				<td><input type='text' name='comment'></td><td><input type='submit' value='Submit Review'></td>
				</tr>
          <% }
         	 	st.close();
         } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {

         }%>
        </table>
        </form>


        <br>
        <br><a href='search.jsp'>Back</a>
</body> 
</html>