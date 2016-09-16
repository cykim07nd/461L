package homework1;

import java.util.Date;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


@Entity
public class Post implements Comparable<Post> {
    @Id Long id;
    User user;
    String content;
    String title;
    Date date;

    @SuppressWarnings("unused")
	private Post() {}
    
    public Post(User user, String content, String title) {
        this.user = user;
        this.content = content;
        this.title = title;
        date = new Date();
    }
    
    public Date getDate() {
    	return date;
    }
    
    public User getUser() {
        return user;
    }

    public String getTitle() {
    	return title;
    }
    
    public String getContent() {
        return content;
    }

    @Override
    public int compareTo(Post other) {
        if (date.after(other.date)) {
            return -1;
        } else if (date.before(other.date)) {
            return 1;
        }
        return 0;
    }

}
