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
		String sql = "";
		return sql;
	}
	
	public static String whosNew() {
		String sql = "";
		return sql;
	}

	public static String whosHot() {
		String sql = "";
		return sql;
	}
	
	public static String whosTop() {
		String sql = "";
		return sql;
	}
	
	public static String popularTags() {
		String sql = "";
		return sql;
	}
	
	public static String favComedian() {
		String sql = "";
		return sql;
	}
	
	public static String productive() {
		String sql = "";
		return sql;
	}
	
	public static String positiveReviewers() {
		String sql = "";
		return sql;
	}
	
	public static String poorYoutubes() {
		String sql = "";
		return sql;
	}
	
	public static String twinUsers() {
		String sql = "";
		return sql;
	}
}