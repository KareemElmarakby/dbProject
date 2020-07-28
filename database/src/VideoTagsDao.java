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

public class VideoTagsDao {
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
    	System.out.println("InitializeDB For VideoTags");
        String sql2 = "CREATE TABLE IF NOT EXISTS VideoTags" +
                       " (url VARCHAR(50), " +
                       " tag VARCHAR(50), " + 
                       " PRIMARY KEY (url, tag)) "; 
        
        String sql3 = "insert into VideoTags(url, tag) values (\"youtube.com/1\", \"cool,fun,tables!\")";
        String sql4 = "insert into VideoTags(url, tag) values (\"youtube.com/2\", \"sick,neat,tables!\")";
        String sql5 = "insert into VideoTags(url, tag) values (\"youtube.com/3\", \"sick,fun,tables!\")";
        String sql6 = "insert into VideoTags(url, tag) values (\"youtube.com/4\", \"cool,neat,tables!\")";
        String sql7 = "insert into VideoTags(url, tag) values (\"youtube.com/5\", \"sick,fun,tables!\")";
        String sql8 = "insert into VideoTags(url, tag) values (\"youtube.com/6\", \"cool,fun,tables!\")";
        String sql9 = "insert into VideoTags(url, tag) values (\"youtube.com/7\", \"sick,neat tables!\")";
        String sql10 = "insert into VideoTags(url, tag) values (\"youtube.com/8\", \"sick,neat,tables!\")";
        String sql11 = "insert into VideoTags(url, tag) values (\"youtube.com/9\", \"bad,dog,fun\")";
        String sql12 = "insert into VideoTags(url, tag) values (\"youtube.com/10\", \"school,students\")";


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
    
    public static void logtags(VideoTags tags) throws SQLException{         	
    try {
    	
    	Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Select a table and then print out its content.");
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost:3306/testdb?"
              + "user=root&password=pass123");
      
		String sql = "insert into YoutubeTags(url, tag) values (" + tags.getUrl() + ", ?)";
		
		int i = 0;
		String[] tagSet = tags.getTags();
		
		while(i < (tagSet.length - 1)) {
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, tagSet[i]);
			preparedStatement.executeUpdate();
		}

		
      System.out.println("Insert is successful!");
      preparedStatement.close();
//      disconnect();
      
    } 
    catch (Exception e) {
         System.out.println(e);
    } 
      
    finally {
      close();
    }
}

    
}