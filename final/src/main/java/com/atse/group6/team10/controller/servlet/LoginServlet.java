package com.atse.group6.team10.controller.servlet;

import com.atse.group6.team10.controller.service.LoginService;
import com.atse.group6.team10.controller.service.UserService;
import com.atse.group6.team10.model.LoginSession;
import com.atse.group6.team10.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (login(req)) {
            // Login succeed, we should auto redirect user if exists.
            String redirectUri = req.getParameter("redirectUri");
            if (redirectUri != null) {
                resp.sendRedirect(redirectUri);
                return;
            }
        }
        resp.sendRedirect("login.jsp?message=Authentification+failed,+please+check+username+and+password.");
    }


    private boolean login(HttpServletRequest req) throws IOException {
        String userMail = req.getParameter("email");
        String password = req.getParameter("password");

        LoginSession existingSession = getOptionalLoginSession(req);

        if(existingSession != null)
            return true;

        LoginService loginService = LoginService.getInstance();
        LoginSession session = loginService.login(userMail, password);
        if (session != null ) {
            // Create Session Data here after successful authenticated.
            req.getSession(true).setAttribute(LoginSession.LOGIN_SESSION_KEY, session);
            return true;
        }

        return false;
    }

    public static LoginSession getOptionalLoginSession(HttpServletRequest req) {
        LoginSession result = null;
        HttpSession session = req.getSession(false);
        if (session != null) {
            result = (LoginSession) session.getAttribute(LoginSession.LOGIN_SESSION_KEY);
        }
        return result;
    }

    public static void logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute(LoginSession.LOGIN_SESSION_KEY);
        }
    }

}
