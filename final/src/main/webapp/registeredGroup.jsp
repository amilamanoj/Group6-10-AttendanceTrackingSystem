<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<%-- //[START imports]--%>
<%@ page import="com.atse.group6.team10.model.Student" %>
<%@ page import="com.atse.group6.team10.controller.StudentService" %>
<%-- //[END imports]--%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>

<body>

<h1>Registration</h1>
<div id="registration">
    <%
        StudentService studentService = StudentService.getInstance();
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user == null) {
            response.sendRedirect("home.jsp");
        } else {

            Student s = studentService.getStudentForUser(user.getUserId());
            if (s != null) {
                pageContext.setAttribute("userId", s.getId());
                pageContext.setAttribute("email", s.getEmail());
                pageContext.setAttribute("group", s.getGroup().get());
            }
        }
    %>

    <p>Welcome back ${fn:escapeXml(email)}.
        <a href="<%= userService.createLogoutURL("/home.jsp") %>">sign out</a>
        <br/> Your user id: ${fn:escapeXml(userId)}
    </p>
    <div>
        <p>You are registered for the group: ${fn:escapeXml(group)}</p>
    </div>
    <div>
        <form action = "/groupListView.jsp" method = "post">
            <input type="submit" name="switchGroup" value="Change Group" />
        </form>
    </div>

</div>

</body>
</html>