package homework1;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 

@SuppressWarnings("serial")
public class Homework1Servlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        String homework1Name = req.getParameter("homework1Name");
        String content = req.getParameter("content");
        String title = req.getParameter("title");
        Post post = new Post(user, content, title);
        ObjectifyService.ofy().save().entity(post).now();
        resp.sendRedirect("/test.jsp?homework1Name=" + homework1Name);
    }
}
