<%--
  Created by IntelliJ IDEA.
  User: Tobias
  Date: 1/22/2018
  Time: 9:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="/login?redirectUri=home.jsp" method="post">
    <div>
            <div>
                <label>E-Mail:</label>
                <input type="text" name="email"/>
            </div>
            <div>
                <label>Password:</label>
                <input type="password" name="password" />
            </div>
    </div>
    <button type="submit" id="loginButton">
        Login
    </button>
</form>
</body>
</html>
