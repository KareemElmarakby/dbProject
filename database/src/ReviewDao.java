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

public class ReviewDao {
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
        
        String sql3 = "insert into Reviews(remark, rating, author, youtubeid) values (\"It was aight\", \"G\", \"user2@gmail.com\", \"youtube.com/3\")";
        String sql4 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was ok\", \"E\", \"user3@gmail.com\", \"youtube.com/1\")";
        String sql5 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was bad\", \"F\", \"user9@gmail.com\", \"youtube.com/2\")";
        String sql6 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was really bad\", \"P\", \"user7@gmail.com\", \"youtube.com/4\")";
        String sql7 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This was special\", \"G\", \"user6@gmail.com\", \"youtube.com/5\")";
        String sql8 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was neat!\", \"E\", \"user1@gmail.com\", \"youtube.com/6\")";
        String sql9 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was meh\", \"F\", \"user5@gmail.com\", \"youtube.com/7\")";
        String sql10 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was terrible\", \"P\", \"user6@gmail.com\", \"youtube.com/8\")";
        String sql11 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was okay for me\", \"G\", \"user3@gmail.com\", \"youtube.com/9\")";
        String sql12 = "insert into Reviews(remark, rating, author, youtubeid) values (\"This video was pretty darn good\", \"G\", \"user3@gmail.com\", \"youtube.com/10\")";
        
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

	public static void logreview(Review re) {
	    try {
	    	
	    	Class.forName("com.mysql.jdbc.Driver");
	      System.out.println("Select a table and then print out its content.");
	      connect = DriverManager
	          .getConnection("jdbc:mysql://localhost:3306/testdb?"
	              + "user=root&password=pass123");
	      
			String sql = "insert into Reviews(remark, rating, author, youtubeid) values (?, ?, ?, ?)";
			
		  preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	      preparedStatement.setString(1, re.getRemark());
	      preparedStatement.setString(2, re.getRating());
	      preparedStatement.setString(3, re.getAuthor());
	      preparedStatement.setString(4, re.getYoutube());
	      
	      preparedStatement.executeUpdate();
			
	      System.out.println("Insert is successful!");
	      preparedStatement.close();
//	      disconnect();
	      
	    } 
	    catch (Exception e) {
	         System.out.println(e);
	    } 
	      
	    finally {
	      close();
	    }
		
	}}
    