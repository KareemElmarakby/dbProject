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
                    
                    
                    try {
                    	String check = request.getParameter("Submit");
                		switch (check) {
                		case "cool":
                			rs = st.executeQuery(DisplayDao.whosCool());
                			valueSet = 3;
                			break;
                		case "new":
                			rs = st.executeQuery(DisplayDao.whosNew());
                			break;
                		case "hot":
                			rs = st.executeQuery(DisplayDao.whosHot());
                			break;
                		case "top":
                			rs = st.executeQuery(DisplayDao.whosTop());
                			break;
                		case "tags":
                			rs = st.executeQuery(DisplayDao.popularTags());
                			break;
                		case "favorite":
                			rs = st.executeQuery(DisplayDao.favComedian());
                			break;
                		case "productive":
                			rs = st.executeQuery(DisplayDao.productive());
                			break;
                		case "reviewers":
                			rs = st.executeQuery(DisplayDao.positiveReviewers());
                			break;
                		case "poor":
                			rs = st.executeQuery(DisplayDao.poorYoutubes());
                			break;
                		case "twins":
                			rs = st.executeQuery(DisplayDao.twinUsers());
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
                			while(i<valueSet){
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
    </body>
</html>