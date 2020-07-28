<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>

 <%
            String email = request.getParameter("user");
            session.setAttribute("email", email);

        %> 


<!DOCTYPE html>
<html>
<body> 

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

			PreparedStatement ps = connect.prepareStatement("select url,title from YoutubeVideos where (Comedians.firstname LIKE ? OR Comedians.lastname LIKE ? OR VideoTags.tag=?) "
         		+ "AND (YoutubeVideos.url = VideoTags.url OR YoutubeVideos.comid = Comedians.comid);");
			ps.setString(1, searching);
			ps.setString(2, searching);
			ps.setString(3, searching);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				
				String url = rs.getString("url");
				String title = rs.getString("title"); %>
				
				<tr>
				<td><input type="hidden" name="url" value=<%=url%>/><%= url %></td>
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