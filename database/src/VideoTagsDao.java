import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VideoTagsDao {
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
    	System.out.println("InitializeDB For VideoTags");
    	String sql1 = "DROP TABLE IF EXISTS VideoTags";
        String sql2 = "CREATE TABLE IF NOT EXISTS VideoTags" +
                       " url VARCHAR(50), " +
                       " tag VARCHAR(50), " + 
                       " PRIMARY KEY (url, tag) "; 
        
        String sql3 = "insert into (url, tag) values (?, ?)";
        String sql4 = "insert into VideoTags(url, tag) values (\"youtube.com/sgduyasd\", \"cool, neat, tables!\")";
        String sql5 = "insert into VideoTags(url, tag) values (\"youtube.com/bhasihdui\", \"cool, neat, tables!\")";
        String sql6 = "insert into VideoTags(url, tag) values (\"youtube.com/dayhwufa\", \"cool, neat, tables!\")";
        String sql7 = "insert into VideoTags(url, tag) values (\"youtube.com/bvfdsagb\", \"cool, neat, tables!\")";
        String sql8 = "insert into VideoTags(url, tag) values (\"youtube.com/yfdayfhiweuh\", \"cool, neat, tables!\")";
        String sql9 = "insert into VideoTags(url, tag) values (\"youtube.com/FWEAGRSG\", \"cool, neat, tables!\")";
        String sql10 = "insert into VideoTags(url, tag) values (\"youtube.com/YGguyahsg\", \"cool, neat, tables!\")";
        String sql11 = "insert into VideoTags(url, tag) values (\"youtube.com/uybadIUs\", \"cool, neat, tables!\")";
        String sql12 = "insert into VideoTags(url, tag) values (\"youtube.com/DAtyggvbt\", \"cool, neat, tables!\")";
        String sql13 = "insert into VideoTags(url, tag) values (\"youtube.com/SADoijsad\", \"cool, neat, tables!\")";

        
        try {
        	
        	Class.forName("com.mysql.jdbc.Driver");
          System.out.println("Select a table and then print out its content.");
          connect = DriverManager
              .getConnection("jdbc:mysql://localhost:3306/testdb?"
                  + "user=root&password=pass123");
          
          statement = connect.createStatement();

          statement.executeUpdate(sql1);
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
          statement.executeUpdate(sql13);
          
        } 
        catch (Exception e) {
             System.out.println(e);
        } 
          
        finally {
          close();
        }
    }
    
}