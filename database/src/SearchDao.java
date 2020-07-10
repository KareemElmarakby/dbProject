import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;

public class SearchDao{
	private static final long serialVersionUID = 1L;
	private static Connection connect = null;
	private static Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
  			          + "user=root&password=pass123");
            System.out.println(connect);
        }
    }
    
    private static void close() {
        try {
          if (resultSet != null) {
            resultSet.close();
          }

          if (statement != null) {
            statement.close();
          }

          if (connect != null) {
            connect.close();
          }
        } catch (Exception e) {

        }
      }

	public static void showResults(String search) {
		try {
	    	Class.forName("com.mysql.jdbc.Driver");
	        System.out.println("Select a table and then print out its content.");
	        connect = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/testdb?"
	                + "user=root&password=pass123");
        
			Statement st = connect.createStatement();
			ResultSet rs;
			String searching = search;

			PreparedStatement ps = connect.prepareStatement("select url,title from YoutubeVideos where (Comedians.firstname=? OR Comedians.lastname=? OR VideoTags.tag=?) "
         		+ "AND (YoutubeVideos.url = VideoTags.url OR YoutubeVideos.comid = Comedians.comid);");
			ps.setString(1, searching);
			ps.setString(2, searching);
			ps.setString(3, searching);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				
				String url = rs.getString("url");
				String title = rs.getString("title");
				
				System.out.println("<tr>");
				System.out.println("<td>" + url + "</td>");
				System.out.println("<td>" + title + "</td>");
				System.out.println("<form action='<%= request.getContextPath() %>/logreview'><td>" + 
				"<select name='rating'><option value='p'>P</option><option value='f'>F</option><option value='g'>G</option><option value='E'>E</option></select>"
						+"</td>");
				System.out.println("<td><input type='text' name='comment'></td><td><input type='submit' value='Submit Review'></td></form>" + 
						"            \r\n" + 
						"            String url = request.getParameter(\"url\");\r\n" + 
						"            session.setAttribute(\"url\", url);");
				System.out.println("</tr>");
          }
         	 	st.close();
         } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {

         }

	}}
    