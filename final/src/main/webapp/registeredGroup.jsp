<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<%-- //[START imports]--%>
<%@ page import="com.atse.group6.team10.model.Student" %>
<%@ page import="com.atse.group6.team10.controller.GroupService" %>
<%@ page import="com.atse.group6.team10.controller.StudentService" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
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

<h1>Registration status</h1>
<div id="registration">
    <%
        StudentService studentService = new StudentService();
        Student s = studentService.getStudentForUser(request.getParameter("userId"));
        if (s != null) {
            pageContext.setAttribute("userId", s.getId());
            pageContext.setAttribute("email", s.getEmail());
            pageContext.setAttribute("group", s.getGroup().get());
        }
    %>
    <div>
        <p>User ${fn:escapeXml(email)} is registered for group ${fn:escapeXml(group)}}</p>
    </div>

    <div>
        <form action = "/unregister" method = "post">
            <input type="submit" name="switchGroup" value="Change Group" />
        </form>
    </div>

</div>

</body>
</html>