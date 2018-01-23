package com.atse.group6.team10.controller.servlet;

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
        //Do nothing if authentification failed
        //TODO: inform user about bad autentification
    }


    private boolean login(HttpServletRequest req) throws IOException {
        String userMail = req.getParameter("email");
        String password = req.getParameter("password");

        UserService userService = UserService.getInstance();
        User user = userService.getUserForEMail(userMail);
        if (user != null && userService.isExpectedPassword(user, password.toCharArray())) {
            // Create Session Data here after successful authenticated.
            LoginSession loginsession = getOptionalLoginSession(req);
            if (loginsession == null) {
                //create new session
                createLoginSession(req, user.getId());
            }
            return true;
        } else {
            //user does not exist or invalid password
        }
        return false;
    }

    protected static LoginSession createLoginSession(HttpServletRequest req, Long userId) {
        LoginSession result = new LoginSession(userId);
        req.getSession(true).setAttribute(LoginSession.LOGIN_SESSION_KEY, result);
        ofy().save().entity(result).now();
        return result;
    }

    public static LoginSession getOptionalLoginSession(HttpServletRequest req) {
        LoginSession result = null;
        HttpSession session = req.getSession(false);
        if (session != null) {
            result = (LoginSession) session.getAttribute(LoginSession.LOGIN_SESSION_KEY);
        }
        return result;
    }

}
