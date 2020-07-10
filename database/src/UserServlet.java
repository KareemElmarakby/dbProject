import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Connection connect = null;
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
            case "/logvideo":
            	logvideo(request, response);
            	break;
            default:          	
            	listPeople(request, response);           	
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void logvideo(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getParameter("email");
        String url = request.getParameter("url");
        String title = request.getParameter("title");
        String desc = request.getParameter("desc");
        String tags = request.getParameter("tags");
        String comedian = request.getParameter("com");
        
        Video yt = new Video(user, url, title, desc, comedian);
        VideoTags ytTags = new VideoTags(url, tags);
        
        try {
			VideoDao.logvideo(yt);
	        VideoTagsDao.logtags(ytTags);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	System.out.println("testing for login");
    	String username = request.getParameter("user");
    	String password = request.getParameter("pass");
    	System.out.println(username);
    	System.out.println(password);
    	try {
    	 /*   connect = DriverManager
    	              .getConnection("jdbc:mysql://localhost:3306/testdb?"
    	                  + "user=root&password=pass123");
    	                  userDao.userCheck(username, password */
        	if(username.equals("root") && password.equals("pass123"))
        		response.sendRedirect("RootHomePage.jsp");
        	else
        		response.sendRedirect("StandardUserHomePage.jsp");	
    	}
    	catch (Exception e) {
    		System.out.println(e);
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
        User newUser = new User(firstName, lastName, age, username, password);
        
        boolean userTest = userDao.usernameDup(username);
        if (userTest == true)
        	response.sendRedirect("registration"); //needs error message
        else {
        userDao.insert(newUser);
        response.sendRedirect("login"); //needs message confirming registration
        
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
        
        System.out.println(firstName);
        
        User user = new User(id, firstName, lastName, age, username, password);
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
    	ComedianDao.InitializeDB();
    	UserDao.InitializeDB();
    	VideoDao.InitializeDB();
    	ReviewDao.InitializeDB();
    	VideoTagsDao.InitializeDB();
    	IsFavoriteDao.InitializeDB();
        response.sendRedirect("login.jsp");

    }
    
    
}