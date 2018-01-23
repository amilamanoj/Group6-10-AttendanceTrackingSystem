package com.atse.group6.team10.controller.service;

import com.atse.group6.team10.model.LoginSession;
import com.atse.group6.team10.model.User;

import javax.servlet.http.HttpServletRequest;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class LoginService {

    private static LoginService service;

    public static LoginService getInstance() {
        if (service == null)
            service = new LoginService();
        return service;
    }


    private LoginSession createLoginSession(Long userId) {
        LoginSession result = new LoginSession(userId);
        ofy().save().entity(result).now();
        return result;
    }


    public LoginSession login(String email, String password) {

        LoginSession session = null;

        UserService userService = UserService.getInstance();
        User user = userService.getUserForEMail(email);
        if (user != null && userService.isExpectedPassword(user, password.toCharArray())) {
            // Create Session Data here after successful authenticated.
            session = createLoginSession(user.getId());
        }
        return session;
    }

}
