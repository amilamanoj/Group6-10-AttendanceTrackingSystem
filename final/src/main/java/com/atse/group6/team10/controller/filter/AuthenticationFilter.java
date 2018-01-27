package com.atse.group6.team10.controller.filter;

import com.atse.group6.team10.model.LoginSession;
import com.atse.group6.team10.utils.AuthentificationUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        // check for special/public pages
        boolean isSpecialPage = isSpecialPage(request);

        // check if the session is valid
        boolean validSession = isValidSession(request);

        if (isSpecialPage || validSession) {
            filterChain.doFilter(request, response);
        } else {
            String loginPageURI = request.getContextPath() + "/login.jsp";
            response.sendRedirect(loginPageURI +
                    "?redirect=" + request.getRequestURI());
        }
    }

    private boolean isSpecialPage(HttpServletRequest request) {
        String loginPageURI = request.getContextPath() + "/login.jsp";
        String loginServletURI = request.getContextPath() + "/login";

        String registrationPageURI = request.getContextPath() + "/registration.jsp";
        String registerURI = request.getContextPath() + "/register";

        String logoutPageURI = request.getContextPath() + "/logout.jsp";

        //rest endpoint
        String restEndpointLoginURI = request.getContextPath() + "/rest/login" ;
        boolean restEndpointLoginRequest = request.getRequestURI().equals(restEndpointLoginURI) ;


        boolean registerRequest = request.getRequestURI().equals(registerURI);
        boolean loadRegistrationPage = request.getRequestURI().equals(registrationPageURI);


        boolean loginRequest = request.getRequestURI().equals(loginServletURI);
        boolean loadLoginPage = request.getRequestURI().equals(loginPageURI);

        boolean loadLogoutPage = request.getRequestURI().equals(logoutPageURI);

        return registerRequest || loadRegistrationPage
                || loginRequest || loadLoginPage || loadLogoutPage || restEndpointLoginRequest;
    }

    private boolean isValidSession(HttpServletRequest request) {
        Cookie sessionCookie = AuthentificationUtils.getCookie(AuthentificationUtils.SESSION_COOKIE, request);
        LoginSession session = AuthentificationUtils.getSessionFromCookie(sessionCookie);
        //TODO check whether the session is valid or not
        return session != null;
    }


    @Override
    public void destroy() {

    }
}
