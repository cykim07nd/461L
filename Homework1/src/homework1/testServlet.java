package homework1;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import com.google.appengine.api.mail.*;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 

@SuppressWarnings("serial")
public class testServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);    
        try{
        	Message emailMessage = new MimeMessage(session);
        	emailMessage.setFrom( new InternetAddress("chanyoung.kim95@gmail.com", "Chan-Young Kim"));
        	emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("cykim07nd@yahoo.com", "Young") );
        	emailMessage.setSubject("emila jklfd");
        	emailMessage.setText("this is working");
        	Transport.send(emailMessage);
        	resp.sendRedirect("/test.jsp");
        } catch (AddressException e){
        	
        } catch (MessagingException e) {
        	
        }
        
	
	}
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
       String msg = "msg";
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        try{
        	Message emailMessage = new MimeMessage(session);
        	emailMessage.setFrom( new InternetAddress("chanyoung.kim95@gmail.com", "Chan-Young Kim"));
        	emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("cykim07nd@yahoo.com", "Young") );
        	emailMessage.setSubject("emila jklfd");
        	emailMessage.setText("fdjklsdjfieoj");
        	Transport.send(emailMessage);
        	resp.sendRedirect("/test.jsp");
        } catch (AddressException e){
        	
        } catch (MessagingException e) {
        	
        }
		resp.sendRedirect("/test.jsp");
    }
}
