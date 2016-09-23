package homework1;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


@Entity
public class Email {
    @Id Long id;
	String email;


    @SuppressWarnings("unused")
	private Email() {}
    
    public Email(String email) {
        this.email = email;
    }
    
      
    public Long getId() {
    	return id;
    }
    
    public String getEmail() {
        return email;
    }

}
