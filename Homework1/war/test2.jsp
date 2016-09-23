<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Collections" %>
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
		<li style="float:right"><a class="active" href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a></li>
<%
    } else {

%>
		<li style="float:right"><a class="active" href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a></li>	
<%
    }
%>
  		
	</ul>
	</div>
  </header>
  <body style="margin: 0;">
  <div class="content">



    <form action="/ofysign" method="post">
       <div>
      	   <h4 style="margin:0">Title</h4>
      	   
      	   <input type="text" size="115" name="title" required>
      </div>
       
       
      <div>
      	   <h4 style="margin:0"> Content</h4>	   
      	   <textarea name="content" rows="15" cols="120" ></textarea>
      </div>
      <br>
      <div><input type="submit" value="Post" /></div>
      <input type="hidden" name="homework1Name" value="${fn:escapeXml(homework1Name)}"/>
    </form>
    </div>
  </body>

</html>

