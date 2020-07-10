import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
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
    
    public static void InitializeDB() {
    	// TODO Auto-generated method stub
    	System.out.println("InitializeDB For Review");
        String sql2 = "CREATE TABLE IF NOT EXISTS Reviews" +
        		       " (reviewid INTEGER NOT NULL AUTO_INCREMENT, " +
                       " remark VARCHAR(100), " +
                       " rating CHAR(1), " + 
                       " author VARCHAR(50) NOT NULL, " + 
                       " youtubeid VARCHAR(150) NOT NULL, " +
                       " PRIMARY KEY(reviewid), " + 
                       " FOREIGN KEY(author) REFERENCES Users(email), " +
                       " FOREIGN KEY(youtubeid) REFERENCES YouTubeVideos(url), " +
                       " CONSTRAINT ratingcr CHECK (rating IN ('P', 'F','G','E')))"; 
        
        String sql3 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was ok\", \"G\", \"Kareem\", \"Chris Rock: Project 1 Blues\")";
        String sql4 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was ok\", \"G\", \"John\", \"Chris Rock: Project 1 Blues\")";
        String sql5 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was ok\", \"G\", \"Joe\", \"Chris Rock: Project 1 Blues\")";
        String sql6 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was ok\", \"G\", \"Dan\", \"Chris Rock: Project 1 Blues\")";
        String sql7 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was ok\", \"G\", \"Bob\", \"Chris Rock: Project 1 Blues\")";
        String sql8 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was ok\", \"G\", \"Chase\", \"Chris Rock: Project 1 Blues\")";
        String sql9 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was ok\", \"G\", \"Tahmid\", \"Chris Rock: Project 1 Blues\")";
        String sql10 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was ok\", \"G\", \"Chris\", \"Chris Rock: Project 1 Blues\")";
        String sql11 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was ok\", \"G\", \"Mike\", \"Chris Rock: Project 1 Blues\")";
        String sql12 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was ok\", \"G\", \"Amanda\", \"Chris Rock: Project 1 Blues\")";
        
        try {
        	
        	Class.forName("com.mysql.jdbc.Driver");
          System.out.println("Select a table and then print out its content.");
          connect = DriverManager
              .getConnection("jdbc:mysql://localhost:3306/testdb?"
                  + "user=root&password=pass123");
          
          statement = connect.createStatement();

          statement.executeUpdate(sql2);
          statement.executeUpdate(sql3);
		  statement.executeUpdate(sql4);
		  statement.executeUpdate(sql5);
		  statement.executeUpdate(sql6);
		  statement.executeUpdate(sql7);
		  statement.executeUpdate(sql8);
		  statement.executeUpdate(sql9);
		  statement.executeUpdate(sql10);
		  statement.executeUpdate(sql11);
		  statement.executeUpdate(sql12);
          
        } 
        catch (Exception e) {
             System.out.println(e);
        } 
          
        finally {
          close();
        }
    }
}