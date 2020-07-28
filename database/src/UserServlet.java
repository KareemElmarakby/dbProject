import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Connection connect = null;
	private static Statement statement = null;
	private static Statement statement2 = null;
	private PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
    private UserDao userDao;

    public void init() {
    	userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
    	
        System.out.println(action);
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
            	insertPeople(request, response);
                break;
            case "/delete":
            	deletePeople(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
            	updatePeople(request, response);
                break;
            case "/initialize":
            	initializeDB(request, response);
            	break;
            case "/login":
            	login(request, response);
            	break;
            case "/userrequest":
            	userRequest(request, response);
            	break;
            case "/logvideo":
            	logvideo(request, response);
            	break;
            case "/searchvideo":
            	results(request, response);
            	break;
            case "/logreview":
            	review(request, response);
            	break;
            case "/displaytrends":
            	trends(request, response);
            	break;
            case "/updateFavCom":
            	favCom(request, response);
            	break;
            default:          	
            	listPeople(request, response);           	
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void userRequest(HttpServletRequest request, HttpServletResponse response) {
    	try {
    		
    		String submit = request.getParameter("menu");
    		String user = request.getParameter("pass");
        	request.setAttribute("email", user);
    		
        	if(submit.equalsIgnoreCase("Search")) {
        		RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");       
            	dispatcher.forward(request, response);
            	}
        	if(submit.equalsIgnoreCase("Favorites")) {
        		RequestDispatcher dispatcher = request.getRequestDispatcher("UserFavoriteComedian.jsp");       
            	dispatcher.forward(request, response);
            	}
        	if(submit.equalsIgnoreCase("Insert")) {
        		RequestDispatcher dispatcher = request.getRequestDispatcher("insertVideo.jsp");       
            	dispatcher.forward(request, response);
            	}
        	if(submit.equalsIgnoreCase("Login")) {
        		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");       
            	dispatcher.forward(request, response);
            	}
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
		
	}
    
    protected void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
    	System.out.println("testing for login");
    	String username = request.getParameter("user");
    	String password = request.getParameter("pass");
    	
    	HttpSession session = request.getSession();
    	String user = request.getParameter("user");
    	session.setAttribute("email", user);
        
    	System.out.println(username);
    	System.out.println(password);
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Handling IsFavorite queries");
            connect = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/testdb?"
                    + "user=root&password=pass123");
            statement = connect.createStatement();
            String search = "SELECT email FROM Users WHERE (email = '" + username + "' AND pass = '" + password + "');";
            ResultSet rs = statement.executeQuery(search);
            
            
        	if(username.equals("root") && password.equals("pass123")) {
        		RequestDispatcher dispatcher = request.getRequestDispatcher("RootHomePage.jsp");       
            	dispatcher.forward(request, response);
            	}
        	if(rs.next()){
        		System.out.println("email is " + session.getAttribute("email"));
        		RequestDispatcher dispatcher = request.getRequestDispatcher("StandardUserHomePage.jsp");       
            	dispatcher.forward(request, response);
            	}	
        	else {
        		System.out.println("The login is invalid");
        		response.sendRedirect("login.jsp");
        	}
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    }

	private void favCom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
        	
        	Class.forName("com.mysql.jdbc.Driver");
          System.out.println("Handling IsFavorite queries");
          connect = DriverManager
              .getConnection("jdbc:mysql://localhost:3306/testdb?"
                  + "user=root&password=pass123");
          HttpSession session = request.getSession();
          String submit = request.getParameter("Submit");
          statement = connect.createStatement();
          
          
          if(submit.equalsIgnoreCase("Add")) {
        	  System.out.println("Adding comedian");
        	  System.out.println(session.getAttribute("email"));
        	  String add = request.getParameter("addCom");
        	  System.out.println(add);
        	  String sql1 = "INSERT INTO isfavorite (email, comid) VALUES ('" + session.getAttribute("email") + "', '" + add + "');";
        	  statement.executeUpdate(sql1);
        	  System.out.println("inserted for user " + session.getAttribute("email"));
          }
          
          if(submit.equalsIgnoreCase("Delete")) {
        	  System.out.println("Deleting comedian");
        	  String delete = request.getParameter("removeCom");
        	  String sql2 = "DELETE FROM isfavorite WHERE (email = '" + session.getAttribute("email") + "' AND comid = '" + delete + "');";
        	  statement.executeUpdate(sql2);
          }
          
        } 
        catch (Exception e) {
             System.out.println(e);
        }
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("UserFavoriteComedianConfirm.jsp");
        dispatcher.forward(request, response);
	}

	private void trends(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String user1 = request.getParameter("user1");
    	String user2 = request.getParameter("user2");
    	RequestDispatcher dispatcher = request.getRequestDispatcher("rootDisplaysResults.jsp");
        dispatcher.forward(request, response);
		
	}

	private void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String remark = request.getParameter("comment");
        String rating = request.getParameter("rating");
        String author = (String)session.getAttribute("email");
        String youtube = request.getParameter("url");
        System.out.println("This url is " + youtube);
        
        
        Review re = new Review(remark, rating, author, youtube);
        ReviewDao.logreview(re);  
        RequestDispatcher dispatcher = request.getRequestDispatcher("StandardUserHomePage.jsp");       
    	dispatcher.forward(request, response);
	}
		

	private void results(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
        	String search = request.getParameter("search");
        
        	request.setAttribute("search", search);       
            RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");       
            dispatcher.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	

	private void logvideo(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getParameter("email");
        String url = request.getParameter("url");
        String title = request.getParameter("title");
        String desc = request.getParameter("desc");
        String tags = request.getParameter("tags");
        String comedianFirst = request.getParameter("comFirst");
        String comedianLast = request.getParameter("comLast");
        String comedianDate = request.getParameter("comDate");
        String comedianPlace = request.getParameter("comPlace");
        int comid = 0;
        
    	try {
        	String sq1 = "SELECT postuser, postdate FROM youtubevideos";
        			
        	Class.forName("com.mysql.jdbc.Driver");
          System.out.println("Select a table and then print out its content.");
          connect = DriverManager
              .getConnection("jdbc:mysql://localhost:3306/testdb?"
                  + "user=root&password=pass123");
          
          statement = connect.createStatement();
          
          ResultSet rs = statement.executeQuery(sq1);
          
          int count = 0;
             
          while(rs.next()) {
        	  System.out.println(count);
        	  if (count >= 5) {
        		  System.out.println("TOO MANY");
        		  response.sendRedirect("TooManyVideos.jsp");
        		  return;
        	  }
        	  String dateUrl = rs.getString(2);
        	  String userUrl = rs.getString(1);
        	  String dateUrlArr[] = dateUrl.split(" ");
        	  System.out.println("Local: " + java.time.LocalDate.now().toString());
        	  System.out.println("Server: " + dateUrlArr[0]);
        	  if (dateUrlArr[0].equalsIgnoreCase(java.time.LocalDate.now().toString())){
        		  count++;
        	  }
        	
        	  statement2 = connect.createStatement();
        	  ResultSet comsearch = statement2.executeQuery("SELECT comid FROM Comedians WHERE" +
        	  												"firstname = " + comedianFirst +
        	  												" AND lastname = " + comedianLast +
        	  												" AND birthdate = " + comedianDate +
        	  												" AND birthplace = " + comedianPlace);
        	  if (comsearch.next()) {
        		  comid = comsearch.getInt(1);
        	  }
        	  else {
        		  String comedianSQL = "insert into Comedians(firstname, lastname, birthday, birthplace) values "
        		  		+ "(?,?,?,?)";
        		  PreparedStatement comedians = (PreparedStatement) connect.prepareStatement(comedianSQL);
        	      comedians.setString(1, comedianFirst);
        	      comedians.setString(2, comedianLast);
        	      comedians.setString(3, comedianDate);
        	      comedians.setString(4, comedianPlace);
        	      
        	      comedians.executeUpdate();
        			
        	      System.out.println("Insert is successful!");
        	      comedians.close();
        	      
        	      ResultSet newcomsearch = statement2.executeQuery("SELECT comid FROM Comedians WHERE" +
							"firstname = " + comedianFirst +
							" AND lastname = " + comedianLast +
							" AND birthdate = " + comedianDate +
							" AND birthplace = " + comedianPlace);
        	      
        	      comid = newcomsearch.getInt(1);
        	  }
        	  
        	  
        	Video video = new Video(user, url, title, desc, comid);
        	VideoDao.logvideo(video);
        	VideoTags inserttags = new VideoTags(url, tags);
        	VideoTagsDao.logtags(inserttags);
          }
          
        } 
        catch (Exception e) {
             System.out.println(e);
        }

        try {
			response.sendRedirect("StandardUserHomePage.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    private void listPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUsers = userDao.listAllUsers();
        request.setAttribute("listUser", listUsers);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleList.jsp");       
        dispatcher.forward(request, response);
    }
 
    // to insert a people
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("InsertPeopleForm.jsp");
        dispatcher.forward(request, response);
    }
 
    // to present an update form to update an  existing Student
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingPeople = userDao.getPeople(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditPeopleForm.jsp");
        request.setAttribute("people", existingPeople);
        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
 
    }
 
    // after the data of a people are inserted, this method will be called to insert the new people into the DB
    // 
    private void insertPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String age = request.getParameter("age");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passCheck = request.getParameter("confirmPassword");
        String gender = request.getParameter("gender");
        User newUser = new User(username, password, firstName, lastName, gender, age);
        
        
        
        if (password != passCheck) { 
            System.out.println("Passwords do not match!");
            response.sendRedirect("registration.jsp");
        } 
        else{ 
        	boolean userTest = userDao.usernameDup(username);
            if (userTest == true) {
            	System.out.println("User exists!");
            	response.sendRedirect("registration.jsp");
            }
            else {
            	userDao.insert(newUser);
            	response.sendRedirect("login.jsp"); //needs message confirming registration
        } 
        
        }
    }
 
    private void updatePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        System.out.println(id);
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String age = request.getParameter("age");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        
        System.out.println(firstName);
        
        User user = new User(username, password, firstName, lastName, gender, age);
        userDao.update(user);
        response.sendRedirect("list");
    }
 
    private void deletePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.delete(id);
        response.sendRedirect("list"); 
    }
    
    private void initializeDB(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException {
    	System.out.println("Initializing DB from servlet");
    	
    	String sql1 = "DROP DATABASE IF EXISTS testdb";
    	String sql2 = "CREATE DATABASE IF NOT EXISTS testdb";
    	String sql3 = "USE testdb";
    	
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
          
        } 
        catch (Exception e) {
        	 System.out.println("broken");
             System.out.println(e);
        }
    	
    	ComedianDao.InitializeDB();
    	UserDao.InitializeDB();
    	IsFavoriteDao.InitializeDB();
    	VideoDao.InitializeDB();
    	ReviewDao.InitializeDB();
    	VideoTagsDao.InitializeDB();
    	
        response.sendRedirect("RootHomePage.jsp");

    }
    
    
}