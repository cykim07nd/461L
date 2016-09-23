package homework1;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.googlecode.objectify.ObjectifyService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CronServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Sendgrid mail = new Sendgrid("ee461l", "461lsucks");
		Calendar cal = Calendar.getInstance();
		Date currentDate = new Date();
		cal.setTime(currentDate);
		cal.add(Calendar.HOUR, -24);
		Date dayBack = cal.getTime();

		ObjectifyService.register(Post.class);
		List<Post> posts = ObjectifyService.ofy().load().type(Post.class).list();
		for (int i = 0; i < 100000; i++) {

		}
		posts = ObjectifyService.ofy().load().type(Post.class).list();
		Collections.sort(posts);
		List<Post> newPosts = new ArrayList<Post>();
		for (Post post : posts) {
			if (post.getDate().compareTo(dayBack) >= 0) {
				newPosts.add(post);
			} else {
				break;
			}
		}
		if (newPosts.isEmpty()) {
			return;
		} else {
			PostConverter converter = new PostConverter();
			String body = converter.convert(newPosts);
			// set email data
			ObjectifyService.register(Email.class);
			List<Email> mails = ObjectifyService.ofy().load().type(Email.class).list();
			for (int i = 0; i < 100000; i++) {

			}
			mails = ObjectifyService.ofy().load().type(Email.class).list();
			for (Email send : mails) {
				mail.setTo(send.getEmail()).setFrom("chanyoung.kim95@gmail.com").setSubject("Blog Updates")
						.setText("Updates from last 24 hours").setHtml(body);
				try {
					mail.send();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}