<%@ page language="java" contentType="text/html"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="width=device-width, initial-scale=1">
        <link rel="styleSheet" href=url>
        <script src=url></script>

        <title>Results</title>
    </head>
    <body>
                <br>
                 <%
                    Class.forName("com.mysql.jdbc.Driver"); 
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?"
          	              + "user=root&password=pass123"); 
                    Statement st= con.createStatement(); 
                    ResultSet rs = null;
                    int i = 1; //set these for how many results we're printing
                    int valueSet = 0;
                    String sql = null;
                    
                    try {
                    	String check = request.getParameter("Submit");
                		switch (check) {
                		case "cool":
                			rs = st.executeQuery("SELECT firstname, lastname FROM comedians " + 
               					 "LEFT JOIN youtubevideos on comedians.comid = youtubevideos.comid " + 
            					 "LEFT JOIN reviews on youtubevideos.url = reviews.youtubeid " + 
            					 "WHERE reviewid IS NOT NULL AND rating = 'E'");
                			valueSet = 3;
                			break;
                		case "new":
                			rs = st.executeQuery("SELECT firstname, lastname FROM comedians " + 
               					 "LEFT JOIN youtubevideos on comedians.comid = youtubevideos.comid " + 
            				     "WHERE DATE(postdate) = curdate()");
                			valueSet = 2;
                			break;
                		case "hot":
                			rs = st.executeQuery("SELECT firstname, lastname, COUNT(rating) FROM comedians " + 
               					 "LEFT JOIN youtubevideos on comedians.comid = youtubevideos.comid " + 
            					 "LEFT JOIN reviews on youtubevideos.url = reviews.youtubeid " + 
            					 "WHERE reviewid IS NOT NULL AND rating = 'E' " + 
            					 "GROUP BY comedians.firstname " + 
            					 "ORDER BY COUNT(rating) DESC LIMIT 3");
                			valueSet = 3;
                			break;
                		case "top":
                			rs = st.executeQuery("SELECT firstname, lastname, COUNT(url) FROM comedians " + 
               					 "LEFT JOIN youtubevideos on comedians.comid = youtubevideos.comid " + 
            					 "LEFT JOIN reviews on youtubevideos.url = reviews.youtubeid " + 
            					 "WHERE reviewid IS NOT NULL " + 
            					 "GROUP BY comedians.firstname " + 
            					 "ORDER BY COUNT(url) DESC " + 
            					 "CASE " + 
            					 "WHEN COUNT(DISTINCT url) = COUNT(url) THEN LIMIT 1 " + 
            					 "END CASE");
                			valueSet = 3;
                			break;
                		case "tags":
                			rs = st.executeQuery("SELECT tag " + 
               					 "FROM videotags " + 
            					 "WHERE((SELECT COUNT(url) FROM Users) = (SELECT COUNT(url) FROM videotags))");
                			valueSet = 1;
                			break;
                		case "favorite":
                			rs = st.executeQuery("SELECT FullName " +
                								 "FROM isfavorite " +
                								 "LEFT JOIN comedians ON comedians.comid = isfavorite.comid " +
                								 "WHERE email = '" + request.getParameter("user1") + "' OR email = '" + request.getParameter("user2") + "' " +
                								 "GROUP BY isfavorite.comid HAVING COUNT(*) > 1");
                			valueSet = 2;
                			break;
                		case "productive":
                			rs = st.executeQuery("SELECT email, COUNT(url) " + 
               					 "FROM youtubevideos " + 
            					 "LEFT JOIN users on youtubevideos.postuser = users.email " + 
            					 "WHERE email IS NOT NULL " + 
            					 "GROUP BY users.email " + 
            					 "ORDER BY COUNT(url) DESC " + 
            					 "CASE " + 
            					 "	WHEN COUNT(DISTINCT url) = COUNT(url) THEN LIMIT 1 " + 
            					 "END");
                			valueSet = 2;
                			break;
                		case "reviewers":
                			rs = st.executeQuery("SELECT firstN, lastN " + 
               					 "FROM users " + 
            					 "LEFT JOIN reviews on reviews.author = users.firstN " + 
            					 "WHERE rating = 'G' OR rating = 'E'");
                			valueSet = 2;
                			break;
                		case "poor":
                			rs = st.executeQuery("SELECT url " + 
               					 "FROM youtubevideos " + 
            					 "LEFT JOIN reviews on youtubevideos.url = reviews.youtubeid " + 
            					 "WHERE rating = 'P'");
                			valueSet = 1;
                			break;
                		case "twins":
                			rs = st.executeQuery("SELECT DISTINCT IsFavorite1.email, IsFavorite2.email" +
                				 "from IsFavorite IsFavorite1 " +
                				 "JOIN isFavorite IsFavorite2 " +
                				 "ON (IsFavorite1.comid = IsFavorite2.comid) " +
                				 "WHERE IsFavorite1.comid IN ( " +
                				 "SELECT IsFavorite.comid " +
                				 "FROM IsFavorite " +
                				 "LEFT OUTER JOIN Users ON (isfavorite.email = users.email) " +
                				 "LEFT OUTER JOIN Comedians ON (isfavorite.comid = comedians.comid) " +
                				 "GROUP BY isfavorite.comid " +
                				 "HAVING COUNT(isfavorite.email) > 1 ) " +
                				 "AND IsFavorite1.email != IsFavorite2.email " +
                				 "AND IsFavorite1.email < IsFavorite2.email"); 
                			valueSet = 2;
                			break;
                		}
                        		
                        	}
                        	catch (Exception e) {
                                System.out.println(e);
                        	}
                    		finally{
                    			
                    		}
                    
                    if(rs.isBeforeFirst())
                    {
                %>
                        <table>
                <%
                        while(rs.next())
                        {  
                %>          <tr>
                <% 
                			while(i<valueSet+1){
                %>
                				<td> <%= rs.getString(i) %> </td>
                <% 			i++; }
                			i = 1;
                %>
                			</tr>
                <%
                        }
                %>
                <%  }
                    else
                    {
                        out.println("No Search Results");
                    }
                %>
                        </table>  
    <a href="rootDisplays.jsp">Return To Root Functions</a>                   
    </body>
</html>