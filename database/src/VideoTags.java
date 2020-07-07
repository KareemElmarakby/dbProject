import java.io.Serializable;

	    
public class VideoTags implements Serializable {
	  
    private static final long serialVersionUID = 1;
    private String url;
    private String[] tags;
    private int id;

		public VideoTags() {
	    }
	 
	 
	    public VideoTags(String url, String tags) {
	        this.url = url;
	        this.tags = tags.split(" ");
	        
	    }
	    
	    public String getUrl() {
	        return url;
	    }
	    public void setUrl(String url) {
	        this.url = url;
	    }
	    
	    public String[] getTags() {
	        return tags;
	    }
	    public void setTags(String tags) {
	        this.tags = tags.split(" ");
	    }
	    
	    
}
