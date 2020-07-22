import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DisplayDao {
	private static final long serialVersionUID = 1L;
	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
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
    
	public static String whosCool() {
		String sql = "SELECT firstname, lastname FROM comedians " + 
					 "LEFT JOIN youtubevideos on comedians.comid = youtubevideos.comid " + 
					 "LEFT JOIN reviews on youtubevideos.url = reviews.youtubeid " + 
					 "WHERE reviewid IS NOT NULL AND rating = 'E'";
		return sql;
	}
	
	public static String whosNew() {
		String sql = "SELECT firstname, lastname FROM comedians " + 
					 "LEFT JOIN youtubevideos on comedians.comid = youtubevideos.comid " + 
				     "WHERE DATE(postdate) = curdate()";
		return sql;
	}

	public static String whosHot() {
		String sql = "SELECT firstname, lastname, COUNT(rating) FROM comedians " + 
					 "LEFT JOIN youtubevideos on comedians.comid = youtubevideos.comid " + 
					 "LEFT JOIN reviews on youtubevideos.url = reviews.youtubeid " + 
					 "WHERE reviewid IS NOT NULL AND rating = 'E' " + 
					 "GROUP BY comedians.firstname " + 
					 "ORDER BY COUNT(rating) DESC LIMIT 3";
		return sql;
	}
	
	public static String whosTop() { // CASE statement is not working, don't know why. Once syntax error is resolved this should work
		String sql = "SELECT firstname, lastname, COUNT(url) FROM comedians " + 
					 "LEFT JOIN youtubevideos on comedians.comid = youtubevideos.comid " + 
					 "LEFT JOIN reviews on youtubevideos.url = reviews.youtubeid " + 
					 "WHERE reviewid IS NOT NULL " + 
					 "GROUP BY comedians.firstname " + 
					 "ORDER BY COUNT(url) DESC " + 
					 "CASE " + 
					 "WHEN COUNT(DISTINCT url) = COUNT(url) THEN LIMIT 1 " + 
					 "END CASE";
		return sql;
	}
	
	public static String popularTags() { // SHOULD work but needs further testing and debugging
		String sql = "SELECT tag " + 
					 "FROM videotags " + 
					 "WHERE((SELECT COUNT(url) FROM Users) = (SELECT COUNT(url) FROM videotags))";
		return sql;
	}
	
	public static String favComedian() { // NOT CURRENTLY WORKING !!!
		String sql = "SELECT firstname, lastname " + 
					 "FROM comedians " + 
					 "LEFT JOIN isfavorite ON comedians.comid = isfavorite.comid " + 
					 "WHERE comedians.comid = isfavorite.comid " + 
					 "AND IN " + 
					 "(SELECT isfavorite.comid " + 
					 "FROM isfavorite " + 
					 "WHERE isfavorite.email = 'user1@gmail.com' AND isfavorite.email = 'user2@gmail.com')";
		return sql;
	}
	
	public static String productive() {
		String sql = "SELECT email, COUNT(url) " + 
					 "FROM youtubevideos " + 
					 "LEFT JOIN users on youtubevideos.postuser = users.email " + 
					 "WHERE email IS NOT NULL " + 
					 "GROUP BY users.email " + 
					 "ORDER BY COUNT(url) DESC " + 
					 "CASE " + 
					 "	WHEN COUNT(DISTINCT url) = COUNT(url) THEN LIMIT 1 " + 
					 "END";
		
		/*
		 * THIS QUERY IS TO RETURN THE LIST OF YOURTUBE LINKS FROM A SPECIFIC USER. REPLACE XXXXX with Users.email of the desired user
		 * 
		  SELECT url 
		  FROM youtubevideos
          LEFT JOIN users on youtubevideos.postuser = users.email
          WHERE postuser = "XXXXX"
		 * 
		 */
		
		return sql;
	}
	
	public static String positiveReviewers() {
		String sql = "SELECT firstN, lastN " + 
					 "FROM users " + 
					 "LEFT JOIN reviews on reviews.author = users.firstN " + 
					 "WHERE rating = 'G' OR rating = 'E'";
		return sql;
	}
	
	public static String poorYoutubes() {
		String sql = "SELECT url " + 
					 "FROM youtubevideos " + 
					 "LEFT JOIN reviews on youtubevideos.url = reviews.youtubeid " + 
					 "WHERE rating = 'P'";
		return sql;
	}
	
	public static String twinUsers() {
		String sql = "";
		return sql;
	}
	
}