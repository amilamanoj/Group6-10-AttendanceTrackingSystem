<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.atse.group6.team10.model.Student" %>
<%@ page import="com.atse.group6.team10.controller.StudentService" %>
<%--
  Created by IntelliJ IDEA.
  User: amila
  Date: 12/13/17
  Time: 2:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Attendance Tracking System</title>
</head>
<body>
<h1>Welcome to Attendance Tracking System</h1>
<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
        pageContext.setAttribute("user", user);
        StudentService studentService = StudentService.getInstance();
        Student student = studentService.getStudentForUser(user.getUserId());
        if (student != null && student.getGroup().get() != null) {
            response.sendRedirect("registeredGroup.jsp");
        } else {
            response.sendRedirect("groupListView.jsp");
        }
    } else {
%>
<p>Hello!
    <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in to get started</a>
</p>
<%
    }
%>
</body>
</html>
