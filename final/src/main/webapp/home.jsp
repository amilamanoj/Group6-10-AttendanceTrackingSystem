<%@ page import="com.atse.group6.team10.model.LoginSession" %>
<%@ page import="com.atse.group6.team10.controller.service.UserService" %>
<%@ page import="com.atse.group6.team10.controller.servlet.LoginServlet" %>
<%@ page import="com.atse.group6.team10.model.User" %>
<%@ page import="com.atse.group6.team10.model.Student" %>
<%@ page import="com.atse.group6.team10.utils.AuthentificationUtils" %><%--
  Created by IntelliJ IDEA.
  User: Tobias
  Date: 1/23/2018
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Attendance Tracker System</title>
</head>
<body>
<%
    LoginSession loginSession = AuthentificationUtils.getOptionalLoginSession(request);
    UserService service = UserService.getInstance();
    User user = service.getUserForId(loginSession.getUser().getId());
    if (user != null && user.isStudent()) {
        Student student = (Student) user;
        if (student != null && student.getGroup() != null) {
            response.sendRedirect("registeredGroup.jsp");
        } else {
            response.sendRedirect("groupListView.jsp");
        }
    }
%>
<h1>Welcome to the Attendance Tracker System!</h1>
</body>
</html>
