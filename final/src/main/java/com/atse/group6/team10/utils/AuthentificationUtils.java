package com.atse.group6.team10.utils;

import com.atse.group6.team10.controller.AuthentificationFilter;
import com.atse.group6.team10.controller.service.ConfigurationService;
import com.atse.group6.team10.model.LoginSession;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class AuthentificationUtils {

    public static String SESSION_COOKIE = "SESSION-COOKIE";

    public static LoginSession getOptionalLoginSession(HttpServletRequest req) {
        Cookie cookie = getCookie(SESSION_COOKIE, req);
        LoginSession result = getSessionFromCookie(cookie);
        return result;
    }

    public static LoginSession getSessionFromCookie(Cookie cookie) {
        LoginSession session = null;
        if (cookie != null) {
            String sessionId = cookie.getValue();
            session = ofy().load().type(LoginSession.class).id(sessionId).now();
        }
        return session;
    }

    public static Cookie getCookie(String cookieName, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        Cookie foundCookie = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookieName.equals(cookies[i].getName()))
                    foundCookie = cookies[i];
            }
        }
        return foundCookie;
    }

    public static Cookie createCookie(LoginSession session) {
        Cookie cookie = new Cookie(SESSION_COOKIE, session != null ? session.getId() : "");
        ConfigurationService configService = ConfigurationService.getInstance();
        int cookieLifetime = configService.getConfig().getInt("cookie.lifetime");
        cookie.setMaxAge(cookieLifetime);
        return cookie;
    }


    public static void destroyCookie(HttpServletRequest req, HttpServletResponse res) {
        Cookie cookie = createCookie(null);
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }

}
