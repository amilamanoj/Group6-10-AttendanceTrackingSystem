<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- //[START imports]--%>
<%@ page import="com.atse.group6.team10.model.Student" %>
<%@ page import="com.atse.group6.team10.controller.service.GroupService" %>
<%@ page import="com.atse.group6.team10.controller.service.UserService" %>
<%-- //[END imports]--%>

<%@ page import="java.util.List" %>
<%@ page import="com.atse.group6.team10.model.Group" %>
<%@ page import="com.atse.group6.team10.model.User" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>

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
        List<Group> groups = GroupService.getInstance().getGroups();
    %>
    <form action="/registerGroup" method="post">
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
