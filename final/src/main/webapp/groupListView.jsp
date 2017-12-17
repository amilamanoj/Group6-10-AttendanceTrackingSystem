<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<%-- //[START imports]--%>
<%@ page import="com.atse.group6.team10.model.Student" %>
<%@ page import="com.atse.group6.team10.controller.GroupService" %>
<%@ page import="com.atse.group6.team10.controller.StudentService" %>
<%-- //[END imports]--%>

<%@ page import="java.util.List" %>
<%@ page import="com.atse.group6.team10.model.Group" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>

<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user == null) {
        pageContext.setAttribute("user", user);
        String loginURL = userService.createLoginURL(request.getRequestURI());
        response.sendRedirect(loginURL);
    }
%>

<script type="text/javascript">
    function toggleExpand(label) {
        var div = label.parentElement;
        if ($(div).hasClass("collapsed")) {
            $(div).removeClass("collapsed");
            $(div).addClass("expanded");
            $(label).toggleClass("icon-expandable icon-collapsable");
        } else if ($(div).hasClass("expanded")) {
            $(div).removeClass("expanded");
            $(div).addClass("collapsed");
            $(label).toggleClass("icon-expandable icon-collapsable");
        }

    }


    function toggleIcons(div) {
        if ($(div).hasClass("collapsed")) {
            $(div).find("expandable-icon").css("display: none;");
            $(div).find("collapsable-icon").css("display: block;");
        } else if ($(div).hasClass("expanded")) {
            $(div).find("expandable-icon").css("display: block;");
            $(div).find("collapsable-icon").css("display: none;");
        }
    }
</script>

<h1>Select your group</h1>
<div id="grouptree">
    <%
        if (user != null) {
            StudentService studentService = StudentService.getInstance();
            Student s = studentService.getStudentForUser(user.getUserId());
            List<Group> groups = GroupService.getInstance().getGroups();

    %>
    <form action="/register" method="post">
        <%
            for (Group g : groups) {
                Long groupId = g.getId();
                pageContext.setAttribute("groupNumber", groupId);
                pageContext.setAttribute("groupName", g.getName());
                pageContext.setAttribute("groupAppointment", g.getFormattedAppointmentDate());
        %>
        <div>
            <div>
                <input type="radio" class="group" name="groupNumber" value="${fn:escapeXml(groupNumber)}">
                <label> Group ${fn:escapeXml(groupNumber)} </label>
                <div>
                    <label>Name: ${fn:escapeXml(groupName)}</label>
                    <label>Date: ${fn:escapeXml(groupAppointment)}</label>
                </div>
            </div>
        </div>
        <%

                }
            }
        %>
        <button type="submit" id="registration">
            Register
        </button>
    </form>

</div>


<body>


</body>
</html>
<%-- //[END all]--%>
