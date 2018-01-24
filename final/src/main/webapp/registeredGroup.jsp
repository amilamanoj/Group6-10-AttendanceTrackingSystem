<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- //[START imports]--%>
<%@ page import="com.atse.group6.team10.model.Student" %>
<%@ page import="com.atse.group6.team10.controller.service.UserService" %>
<%-- //[END imports]--%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="com.atse.group6.team10.controller.servlet.LoginServlet" %>
<%@ page import="com.atse.group6.team10.model.LoginSession" %>
<%@ page import="com.atse.group6.team10.model.User" %>
<%@ page import="com.atse.group6.team10.utils.AuthentificationUtils" %>
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
        LoginSession loginSession = AuthentificationUtils.getOptionalLoginSession(request);
        UserService service = UserService.getInstance();
        User user = service.getUserForId(loginSession.getUser().getId());
        if (user != null && user.isStudent()) {
            Student s = (Student) user;
            pageContext.setAttribute("userId", s.getId());
            pageContext.setAttribute("email", s.getEmail());
            pageContext.setAttribute("group", s.getGroup().get());
        }
    %>

    <p>Welcome back ${fn:escapeXml(email)}.
        <a href="/logout.jsp">sign out</a>
        <br/> Your user id: ${fn:escapeXml(userId)}
    </p>
    <div>
        <p>You are registered for the group: ${fn:escapeXml(group)}</p>
    </div>
    <div>
        <form action="/groupListView.jsp" method="post">
            <input type="submit" name="switchGroup" value="Change Group"/>
        </form>
    </div>

</div>

</body>
</html>