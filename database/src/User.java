import java.io.Serializable;

public class User implements Serializable {
  
    private static final long serialVersionUID = 1;
    private String firstName;
    private String lastName;
    private String age;
    private String username;
    private String password;
	private String gender;

    
    public User() {
    }
  
    public User(String username, String password, String firstName, String lastName, String gender, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
        this.gender = gender;
    }
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    private boolean checkUsername(String username) {
    	return true;
    }
    
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

}