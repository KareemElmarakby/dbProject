import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Initialize  {
  private static Connection connect = null;
  private static Statement statement = null;
  private static PreparedStatement preparedStatement = null;
  private static ResultSet resultSet = null;

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private static void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    System.out.println("print result from a table..");
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      Integer id = resultSet.getInt("id");
      String name = resultSet.getString("Name");
      String address = resultSet.getString("Address");
      String status = resultSet.getString("Status");
      System.out.println("id " + id);
      System.out.println("name: " + name);
      System.out.println("address: " + address);
      System.out.println("status: " + status);
      System.out.println("");
    }
  }

  // You need to close the resultSet
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
	System.out.println("InitializeDB from inside the function!");
	String sql1 = "DROP TABLE IF EXISTS Video";
    String sql2 = "CREATE TABLE IF NOT EXISTS Video" +
                   " Url VARCHAR(50), " +
                   " Title VARCHAR(50), " + 
                   " Desc VARCHAR(400), " + 
                   " Time DATETIME, " + 
                   " PRIMARY KEY (Url) "; 
    
    String sql3 = "insert into  Video(Url, Title, Desc) values (?, ?, ?)";
    String sql4 = "insert into Video(Url, Title, Desc) values (\"https://www.youtube.com/watch?v=tDeMImqedek\", \"Tales From Australia\", \"Happy throwback Thursday from 2019!\")";
    
    try {
      System.out.println("Select a table and then print out its content.");
      // This will load the MySQL driver, each DB has its own driver
      // Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost:3306/testdb?"
              + "user=root&password=pass123");

        

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();

      statement.executeUpdate(sql1);
      statement.executeUpdate(sql2);
      statement.executeUpdate(sql3);
      statement.executeUpdate(sql4);


      // see the results 
      resultSet = statement.executeQuery("select * from Student");
      writeResultSet(resultSet);
      
    } catch (Exception e) {
         System.out.println(e);
    } finally {
      close();
    }
}
} 
