import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
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
    	System.out.println("InitializeDB for Users");
        String sql2 = "CREATE TABLE IF NOT EXISTS Users " +
                       "(email VARCHAR(50), " +
                       "pass VARCHAR(50), " + 
                       "firstN VARCHAR(50), " + 
                       "lastN VARCHAR(50), " +
                       "gender CHAR(1), " + 
                       "age INTEGER, " +
                       "PRIMARY KEY (email))";
        
        String sql3 = "insert into Users(email, pass, firstN, lastN, gender, age) values (\"user1@gmail.com\", \"secretpassword1\", \"Kareem\", \"First\", \"m\", \"21\")";
        String sql4 = "insert into Users(email, pass, firstN, lastN, gender, age) values (\"user2@gmail.com\", \"secretpassword2\", \"John\", \"Second\", \"f\", \"23\")";
        String sql5 = "insert into Users(email, pass, firstN, lastN, gender, age) values (\"user3@gmail.com\", \"secretpassword3\", \"Joe\", \"Third\", \"m\", \"55\")";
        String sql6 = "insert into Users(email, pass, firstN, lastN, gender, age) values (\"user4@gmail.com\", \"secretpassword4\", \"Dan\", \"Fourth\", \"f\", \"33\")";
        String sql7 = "insert into Users(email, pass, firstN, lastN, gender, age) values (\"user5@gmail.com\", \"secretpassword5\", \"Bob\", \"Fifth\", \"m\", \"12\")";
        String sql8 = "insert into Users(email, pass, firstN, lastN, gender, age) values (\"user6@gmail.com\", \"secretpassword6\", \"Chase\", \"Sixth\", \"f\", \"56\")";
        String sql9 = "insert into Users(email, pass, firstN, lastN, gender, age) values (\"user7@gmail.com\", \"secretpassword7\", \"Tahmid\", \"Seventh\", \"m\", \"4\")";
        String sql10 = "insert into Users(email, pass, firstN, lastN, gender, age) values (\"user8@gmail.com\", \"secretpassword8\", \"Chris\", \"Eight\", \"f\", \"75\")";
        String sql11 = "insert into Users(email, pass, firstN, lastN, gender, age) values (\"user9@gmail.com\", \"secretpassword9\", \"Mike\", \"Ninth\", \"m\", \"13\")";
        String sql12 = "insert into Users(email, pass, firstN, lastN, gender, age) values (\"user10@gmail.com\", \"secretpassword10\", \"Amanda\", \"Tenth\", \"f\", \"18\")";

        String sql13 = "ALTER TABLE Users ADD FullName VARCHAR(50)";
        String sql14 = "UPDATE Users SET FullName = CONCAT(firstN,' ',lastN)";
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
		  statement.executeUpdate(sql13);
		  statement.executeUpdate(sql14);
		  
		  System.out.println("AFTER INIT EXECUTION");
        } 
        catch (Exception e) {
             System.out.println(e);
        } 
          
        finally {
          close();
        }
    }
    
    public List<User> listAllUsers() throws SQLException {
        List<User> listUsers = new ArrayList<User>();        
        String sql = "SELECT * FROM User";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String age = resultSet.getString("age");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
             
            User user = new User(id, firstName, lastName, age, username, password);
            listUsers.add(user);
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listUsers;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
         
    public boolean insert(User user) throws SQLException {
    	connect_func();         
		String sql = "INSERT INTO User" +
	            "  (id, first_name, last_name, age, username, password) VALUES " +
	            " (?, ?, ?, ?, ?,?);";
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getAge());
        preparedStatement.setString(5, user.getUsername());
        preparedStatement.setString(6, user.getPassword());
//		preparedStatement.executeUpdate();
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowInserted;
    }     
    
    public boolean usernameDup(String username) throws SQLException {
        boolean check = false;
        try {
        	String sql = "SELECT * FROM users WHERE username = ?";        
            connect_func();
            preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery(sql);
            if(resultSet.next())
            	check = true;
        }
        catch (Exception e) {
        	System.out.println(e);
        }
        finally {
            preparedStatement.close();
        }
        return check;     
    }
    
    public boolean userCheck(String username, String password) throws SQLException {
        boolean check = false;
        try {
        	String sql = "SELECT * FROM users WHERE username = ?" + " and password = ?";        
            connect_func();
            preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery(sql);
            if(resultSet.next())
            	check = true;
        }
        catch (Exception e) {
        	System.out.println(e);
        }
        finally {
            preparedStatement.close();
        }
        return check;     
    }
     
    public boolean delete(int userid) throws SQLException {
        String sql = "DELETE FROM student WHERE id = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, userid);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowDeleted;     
    }
     
    public boolean update(User user) throws SQLException {
        String sql = "update student set firstName=?, lastName=?, age=?, username=?, password=? where id = ?";
        connect_func();
        
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getAge());
        preparedStatement.setString(5, user.getUsername());
        preparedStatement.setString(6, user.getPassword());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowUpdated;     
    }
	
    public User getPeople(int id) throws SQLException {
    	User user = null;
        String sql = "SELECT * FROM student WHERE id = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String age = resultSet.getString("age");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
             
            user = new User(id, firstName, lastName, age, username, password);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}