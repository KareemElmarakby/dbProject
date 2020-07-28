import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IsFavoriteDao {
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
  			          + "user=root&password=pass1234");
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
    	System.out.println("InitializeDB For IsFavorite");
        String sql1 = "CREATE TABLE IF NOT EXISTS IsFavorite" +
                       " (email VARCHAR(50), " +
                       " comid INTEGER, " + 
                       " PRIMARY KEY (email, comid), " + 
                       " FOREIGN KEY (email) REFERENCES Users(email), " +
                       " FOREIGN KEY (comid) REFERENCES Comedians(comid))"; 
        
        String sql2 = "insert into IsFavorite(email, comid) values (\"user1@gmail.com\", \"3\")";
        String sql3 = "insert into IsFavorite(email, comid) values (\"user1@gmail.com\", \"4\")";
        String sql4 = "insert into IsFavorite(email, comid) values (\"user1@gmail.com\", \"5\")";
        String sql5 = "insert into IsFavorite(email, comid) values (\"user1@gmail.com\", \"6\")";
        String sql6 = "insert into IsFavorite(email, comid) values (\"user3@gmail.com\", \"3\")";
        String sql7 = "insert into IsFavorite(email, comid) values (\"user2@gmail.com\", \"3\")";
        String sql8 = "insert into IsFavorite(email, comid) values (\"user2@gmail.com\", \"2\")";
        String sql9 = "insert into IsFavorite(email, comid) values (\"user2@gmail.com\", \"1\")";
        
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
          
        } 
        catch (Exception e) {
             System.out.println(e);
        } 
          
        finally {
          close();
        }
    }
    
}