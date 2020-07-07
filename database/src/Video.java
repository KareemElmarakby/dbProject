import java.io.Serializable;

	    
public class Video implements Serializable {
	  
    private static final long serialVersionUID = 1;
    private String email;
    private String url;
    private String title;
    private String desc;
    private String com;
    private int id;

		public Video() {
	    }
	 
	    public Video(String email) {
	        this.email = email;
	    }
	 
	    public Video(String email, String url, String title, String desc, String com) {
	        this.email = email;
	        this.url = url;
	        this.title = title;
	        this.desc = desc;
	        this.com = com;
	    }
	    
	    public String getEmail() {
	        return email;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    
	    
	    public String getUrl() {
	        return url;
	    }
	    public void setUrl(String url) {
	        this.url = url;
	    }
	    
	    
	    public String getTitle() {
	        return title;
	    }
	    public void setTitle(String title) {
	        this.title = title;
	    }
	    
	    
	    public String getDesc() {
	        return desc;
	    }
	    public void setDesc(String desc) {
	        this.desc = desc;
	    }
	    
	    public String getCom() {
	        return com;
	    }
	    public void setCom(String com) {
	        this.com = com;
	    }
}
