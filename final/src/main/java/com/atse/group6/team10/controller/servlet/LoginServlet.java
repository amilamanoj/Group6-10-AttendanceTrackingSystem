package com.atse.group6.team10.controller.servlet;

import com.atse.group6.team10.controller.service.LoginService;
import com.atse.group6.team10.model.LoginSession;
import com.atse.group6.team10.utils.AuthentificationUtils;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (login(req, resp)) {
            // Login succeed, we should auto redirect user if exists.
            String redirectUri = req.getParameter("redirectUri");
            if (redirectUri != null) {
                resp.sendRedirect(redirectUri);
                return;
            }
        }
        resp.sendRedirect("login.jsp?message=Authentification+failed,+please+check+username+and+password.");
    }


    private boolean login(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String userMail = req.getParameter("email");
        String password = req.getParameter("password");

        LoginSession existingSession = AuthentificationUtils.getOptionalLoginSession(req);

        if(existingSession != null)
            return true;

        LoginService loginService = LoginService.getInstance();
        LoginSession session = loginService.login(userMail, password);
        if (session != null ) {
            // Create Session Data here after successful authenticated.
//            req.getSession(true).setAttribute(LoginSession.LOGIN_SESSION_KEY, session);
            Cookie sessionCookie = AuthentificationUtils.createCookie(session);
            response.addCookie(sessionCookie);
            return true;
        }

        return false;
    }





}
