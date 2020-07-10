import java.io.Serializable;

	    
public class Review implements Serializable {
	  
    private static final long serialVersionUID = 1;
    private String remark;
    private String rating;
    private String author;
    private String youtube;
    private int reviewid;

		public Review() {
	    }
	 
	 
	    public Review(String remark, String rating, String author, String youtube) {
	        this.remark = remark;
	        this.rating = rating;
	        this.author = author;
	        this.youtube = youtube;
	    }
	    
	    public String getRemark() {
	        return remark;
	    }
	    public void setRemark(String remark) {
	    	this.remark = remark;
	    }
	    
	    
	    public String getRating() {
	        return rating;
	    }
	    public void setRating(String rating) {
	    	this.rating = rating;
	    }
	    
	    
	    public String getAuthor() {
	        return author;
	    }
	    public void setAuthor(String author) {
	    	this.author = author;
	    }
	    
	    
	    public String getYoutube() {
	        return youtube;
	    }
	    public void setYoutube(String youtube) {
	    	this.youtube = youtube;
	    }
	    
}