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
    	String sql1 = "DROP TABLE IF EXISTS Comedians";
        String sql2 = "CREATE TABLE IF NOT EXISTS Comedians" +
                       " comid INTEGER, " +
                       " firstname VARCHAR(50), " + 
                       " lastname VARCHAR(50), " + 
                       " birthday DATE, " +
                       " birthplace VARCHAR(50), " + 
                       " PRIMARY KEY (comid) "; 
        
        String sql3 = "insert into Comedians(comid, firstname, lastname, birthday, birthplace) values (?, ?, ?, ?, ?)";
        String sql4 = "insert into Comedians(comid, firstname, lastname, birthday, birthplace) values (\"1\", \"First1\", \"Last1\", \"March 10\", \"Dakota\")";
        String sql5 = "insert into Comedians(comid, firstname, lastname, birthday, birthplace) values (\"2\", \"First2\", \"Last2\", \"March 11\", \"Michigan\")";
        String sql6 = "insert into Comedians(comid, firstname, lastname, birthday, birthplace) values (\"3\", \"First3\", \"Last3\", \"March 12\", \"Texas\")";
        String sql7 = "insert into Comedians(comid, firstname, lastname, birthday, birthplace) values (\"4\", \"First4\", \"Last4\", \"March 13\", \"Maine\")";
        String sql8 = "insert into Comedians(comid, firstname, lastname, birthday, birthplace) values (\"5\", \"First5\", \"Last5\", \"March 14\", \"Washington\")";
        String sql9 = "insert into Comedians(comid, firstname, lastname, birthday, birthplace) values (\"6\", \"First6\", \"Last6\", \"March 15\", \"New York\")";
        String sql10 = "insert into Comedians(comid, firstname, lastname, birthday, birthplace) values (\"7\", \"First7\", \"Last7\", \"March 16\", \"Florida\")";
        String sql11 = "insert into Comedians(comid, firstname, lastname, birthday, birthplace) values (\"8\", \"First8\", \"Last8\", \"March 17\", \"California\")";
        String sql12 = "insert into Comedians(comid, firstname, lastname, birthday, birthplace) values (\"9\", \"First9\", \"Last9\", \"March 18\", \"Kansas\")";
        String sql13 = "insert into Comedians(comid, firstname, lastname, birthday, birthplace) values (\"10\", \"First10\", \"Last10\", \"March 19\", \"Tennessee\")";
        
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