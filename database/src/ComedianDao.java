import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ComedianDao {
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
    	System.out.println("InitializeDB For Comedians");
        String sql2 = "CREATE TABLE IF NOT EXISTS Comedians" +
                       " (comid INT NOT NULL AUTO_INCREMENT, " +
                       " firstname VARCHAR(50), " + 
                       " lastname VARCHAR(50), " + 
                       " birthday DATE, " +
                       " birthplace VARCHAR(50), " + 
                       " PRIMARY KEY (comid)) "; 
        
        String sql3 = "insert into Comedians(firstname, lastname, birthday, birthplace) values (\"First1\", \"Last1\", \"1984-02-14\", \"Dakota\")";
        String sql4 = "insert into Comedians(firstname, lastname, birthday, birthplace) values (\"First2\", \"Last2\", \"1984-03-14\", \"Michigan\")";
        String sql5 = "insert into Comedians(firstname, lastname, birthday, birthplace) values (\"First3\", \"Last3\", \"1984-04-14\", \"Texas\")";
        String sql6 = "insert into Comedians(firstname, lastname, birthday, birthplace) values (\"First4\", \"Last4\", \"1984-05-14\", \"Maine\")";
        String sql7 = "insert into Comedians(firstname, lastname, birthday, birthplace) values (\"First5\", \"Last5\", \"1984-06-14\", \"Washington\")";
        String sql8 = "insert into Comedians(firstname, lastname, birthday, birthplace) values (\"First6\", \"Last6\", \"1984-07-14\", \"New York\")";
        String sql9 = "insert into Comedians(firstname, lastname, birthday, birthplace) values (\"First7\", \"Last7\", \"1984-08-14\", \"Florida\")";
        String sql10 = "insert into Comedians(firstname, lastname, birthday, birthplace) values (\"First8\", \"Last8\", \"1984-09-14\", \"California\")";
        String sql11 = "insert into Comedians(firstname, lastname, birthday, birthplace) values (\"First9\", \"Last9\", \"1984-10-14\", \"Kansas\")";
        String sql12 = "insert into Comedians(firstname, lastname, birthday, birthplace) values (\"First10\", \"Last10\", \"1984-11-14\", \"Tennessee\")";
        
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