<%@ page language="java" contentType="text/html"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="width=device-width, initial-scale=1">
        <link rel="styleSheet" href=url>
        <script src=url></script>
        <style>
			table, th, td {
  			border: 1px solid black;
			}
		</style>

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
                    String sql = null;
                    boolean twinFormat = false;
                    
                    try {
                    	String check = request.getParameter("Submit");
                		switch (check) {
                		case "cool":
                			rs = st.executeQuery("SELECT firstname, lastname FROM comedians " + 
               					 "LEFT JOIN youtubevideos on comedians.comid = youtubevideos.comid " + 
            					 "LEFT JOIN reviews on youtubevideos.url = reviews.youtubeid " + 
            					 "WHERE reviewid IS NOT NULL AND rating = 'E'");
                			break;
                		case "new":
                			rs = st.executeQuery("SELECT firstname, lastname FROM comedians " + 
               					 "LEFT JOIN youtubevideos on comedians.comid = youtubevideos.comid " + 
            				     "WHERE DATE(postdate) = curdate()");
                			break;
                		case "hot":
                			rs = st.executeQuery("SELECT firstname, lastname, COUNT(rating) FROM comedians " + 
               					 "LEFT JOIN youtubevideos on comedians.comid = youtubevideos.comid " + 
            					 "LEFT JOIN reviews on youtubevideos.url = reviews.youtubeid " + 
            					 "WHERE reviewid IS NOT NULL AND rating = 'E' " + 
            					 "GROUP BY comedians.firstname " + 
            					 "ORDER BY COUNT(rating) DESC LIMIT 3");
                			break;
                		case "top": // Still only returns 1 value, rewritten to use MAX
                			rs = st.executeQuery("SELECT FullName, MAX(x.url_count) " +
                								 "FROM ( " +
                								 "SELECT FullName, COUNT(url) as url_count " +
                								 "FROM comedians " +
                								 "LEFT JOIN youtubevideos on comedians.comid = youtubevideos.comid " +
                								 "LEFT JOIN reviews ON youtubevideos.url = reviews.youtubeid " +
                								 "WHERE reviewid IS NOT NULL " +
                								 "GROUP BY comedians.firstname " +
                								 ") x");
                			break;
                		case "tags":
                			rs = st.executeQuery("SELECT tag " +
                					"FROM Videotags " +
                					"GROUP BY tag " +
                					"HAVING COUNT(tag) = COUNT(DISTINCT url) " +
                					"ORDER BY COUNT(Distinct url) DESC LIMIT 1");
                			break;
                		case "favorite":
                			rs = st.executeQuery("SELECT FullName " +
                								 "FROM isfavorite " +
                								 "LEFT JOIN comedians ON comedians.comid = isfavorite.comid " +
                								 "WHERE email = '" + request.getParameter("user1") + "' OR email = '" + request.getParameter("user2") + "' " +
                								 "GROUP BY isfavorite.comid HAVING COUNT(*) > 1");
                			break;
                		case "productive":
                			rs = st.executeQuery("SELECT FullName, Count(url) " +
                								 "FROM youtubevideos " +
                								 "LEFT JOIN users ON youtubevideos.postuser = users.email " +
                								 "WHERE email is not null " +
                								 "GROUP BY users.email " +
                								 "ORDER BY count(url) desc");
                			break;
                		case "reviewers":
                			rs = st.executeQuery("SELECT firstN, lastN " + 
               					 "FROM users " + 
            					 "LEFT JOIN reviews on reviews.author = users.firstN " + 
            					 "WHERE rating = 'G' OR rating = 'E'");
                			break;
                		case "poor":
                			rs = st.executeQuery("SELECT url " + 
               					 "FROM youtubevideos " + 
            					 "LEFT JOIN reviews on youtubevideos.url = reviews.youtubeid " + 
            					 "WHERE rating = 'P'");
                			break;
                		case "twins":
                			twinFormat = true;
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
                			break;
                		}
                        		
                        	}
                        	catch (Exception e) {
                                System.out.println(e);
                        	}
                    		finally{
                    			
                    		}
                    
                    ResultSetMetaData metadata = rs.getMetaData();
                    int numberOfColumns = metadata.getColumnCount();
				
                    if (twinFormat){
                    	if (rs.isBeforeFirst()){
                            %>
                                    <table>
                            <%  while(rs.next()) {
                            			int i = 1;%>
                                      <tr> 
                                      	<% while(i <= numberOfColumns){ %>
                            	  		<td> <%= rs.getString(i) %> </td>
                            			<% 		i++; }  %>
                            		  </tr>
                            <% }} 
                            	else{ out.println("No Results Found");}%>

                                    </table> 
                    }
                    
                    else{
                    if (rs.isBeforeFirst()){
                %>
                        <table>
                <%  while(rs.next()) {
                			int i = 1;%>
                          <tr> 
                          	<% while(i <= numberOfColumns){ %>
                	  		<td> <%= rs.getString(i) %> </td>
                			<% 		i++; }  %>
                		  </tr>
                <% }} 
                	else{ out.println("No Results Found");}%>

                        </table>  
                        <%} %>
    <a href="rootDisplays.jsp">Return To Root Functions</a>                   
    </body>
</html>