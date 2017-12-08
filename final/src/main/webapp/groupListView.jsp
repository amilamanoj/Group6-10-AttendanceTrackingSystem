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

<h1>Groupregistration system</h1>
<div id="grouptree">
    <%
        if (user != null) {
            StudentService studentService = new StudentService();
            Student s = studentService.getStudentForUser(user.getUserId());

            GroupService gService = new GroupService();
            Map<Integer, List<Integer>> groupTeamMap = gService.getGroupTeamsMapping();
            for (Entry<Integer, List<Integer>> e : groupTeamMap.entrySet()) {
                Integer groupNumber = e.getKey();
                pageContext.setAttribute("groupNumber", groupNumber);

    %>
    <form action="/register" method="post">
        <div class="group collapsed">
            <label class="icon-expandable" onclick="toggleExpand(this)"> Group '${fn:escapeXml(groupNumber)}' </label>
            <%
                for (Integer teamNumber : e.getValue()) {
                    pageContext.setAttribute("teamNumber", teamNumber);
            %>
            <div>
                <input type="radio" class="group" name="groupNumber" value="${fn:escapeXml(groupNumber)}">
                <input type="hidden" class="team" name="teamNumber" value="${fn:escapeXml(teamNumber)}">
                <label> Group '${fn:escapeXml(groupNumber)}' | Team '${fn:escapeXml(teamNumber)}'</label>
            </div>

            <%
                }
            %>
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
