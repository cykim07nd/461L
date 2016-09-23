package homework1;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.googlecode.objectify.ObjectifyService;
import homework1.Sendgrid;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class testServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String email = req.getParameter("email");
		Email mail = new Email(email);
		boolean exist = false;

		ObjectifyService.register(Email.class);
		List<Email> posts = ObjectifyService.ofy().load().type(Email.class).list();
		for (int i = 0; i < 100000; i++) {
		}
		posts = ObjectifyService.ofy().load().type(Email.class).list();
		for (Email mails : posts) {
			if (mails.getEmail().equals(email)) {
				ObjectifyService.ofy().delete().type(Email.class).id(mails.getId());
				exist = true;
				Sendgrid mailSend = new Sendgrid("ee461l", "461lsucks");

				// set email data
				mailSend.setTo(email).setFrom("chanyoung.kim95@gmail.com").setSubject("Unsubscribed")
						.setText("Unsubscribed")
						.setHtml("<strong>You have been unsubscribed from the mailing list</strong>");

				// send your message
				try {
					mailSend.send();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			}
		}
		if (!exist) {
			ObjectifyService.ofy().save().entity(mail).now();
			Sendgrid mailSend = new Sendgrid("ee461l", "461lsucks");

			// set email data
			mailSend.setTo(email).setFrom("chanyoung.kim95@gmail.com").setSubject("Subscribed").setText("Subscribed")
					.setHtml("<strong>You have been Subscribed to the mailing list</strong>");

			// send your message
			try {
				mailSend.send();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		resp.sendRedirect("/test.jsp");
	}
}
