<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="homework1.Post" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.googlecode.objectify.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
		<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
		<title> EE 461 Homework Blog </title>
  </head>
  <header>
  	<div class = "ban">
  		<div class = "banner">
  		<h1> EE 461L Homework Blog </h1>
  		</div>
  		<div class = "names">
  		<h6> Chan-Young Kim and Haoran Niu</h4>
  		</div>
  	</div>
  	<div class ="navbar">
  	<ul>
  		<li><a href="/test.jsp">Home</a></li>
  		<li><a href="/test3.jsp">All Posts</a></li>
  		<%

    String homework1Name = request.getParameter("homework1Name");
    if (homework1Name == null) {
        homework1Name = "default";
    }
    pageContext.setAttribute("homework1Name", homework1Name);
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
   	    pageContext.setAttribute("user", user);
%>
		<li style="float:right"><a class="active" href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Log out</a></li>
<%
    } else {

%>
		<li style="float:right"><a class="active" href="<%= userService.createLoginURL(request.getRequestURI()) %>">Log in</a></li>	
<%
    }
%>
  		
	</ul>
	</div>
  </header>
  <body style="margin: 0;">
  <div class="content">

<%

    // Run an ancestor query to ensure we see the most up-to-date
    // view of the Greetings belonging to the selected Guestbook.
    
	ObjectifyService.register(Post.class);

	List<Post> posts = ObjectifyService.ofy().load().type(Post.class).list();   
	Collections.sort(posts); 

    if (posts.isEmpty()) {
%>
        <p>No posts found.</p>
<%
    } else {
	    for (Post post : posts) {
        	pageContext.setAttribute("post_title" , post.getTitle());
            pageContext.setAttribute("post_date", post.getDate());
%>
			<h4>${fn:escapeXml(post_title)}</h4>
<%
            pageContext.setAttribute("post_content",
                                     post.getContent());
            if (post.getUser() == null) {
%>
                <p style="font-size:80%;">Posted by:Anonymous</p>
                <p style="font-size:80%;">${fn:escapeXml(post_date)}</p>
<%
            } else {
                pageContext.setAttribute("post_user",
                                         post.getUser());
%>
                <p style="font-size:80%;">Posted by:${fn:escapeXml(post_user.nickname)}</p>
				<p style="font-size:80%;">${fn:escapeXml(post_date)}</p>
<%
            }
%>
            <p>${fn:escapeXml(post_content)}</p>
            <br>
<%
        }
    }
%>
<%     if (user != null) {
%>
	<a class="button" href="/test2.jsp">Make a new post</a>
	<br>
	<br>
<%
}
%>
    </div>
  </body>

</html>

