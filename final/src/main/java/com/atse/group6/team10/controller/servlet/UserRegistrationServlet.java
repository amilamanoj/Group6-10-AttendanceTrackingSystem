package com.atse.group6.team10.controller.servlet;

import com.atse.group6.team10.controller.service.LoginService;
import com.atse.group6.team10.controller.service.UserService;
import com.atse.group6.team10.model.LoginSession;
import com.atse.group6.team10.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userMail = req.getParameter("email");
        String password = req.getParameter("password");

        UserService userService = UserService.getInstance();
        User user = userService.getUserForEMail(userMail);
        boolean isStudent = true;

        if (user != null) {

            // useraccount for email already exists
            resp.sendRedirect("loginPage.jsp");
        } else {
            //create new user
            if (isStudent) {
                User newUser = userService.createStudent(userMail, password);
                LoginService loginService = LoginService.getInstance();
                LoginSession session = loginService.login(userMail, password);
                if (session != null) {
                    // Create Session Data here after successful authenticated.
                    req.getSession(true).setAttribute(LoginSession.LOGIN_SESSION_KEY, session);
                }
            } else {
                userService.createTutor(userMail, password);
            }
            resp.sendRedirect("groupListView.jsp");
        }
    }
}
