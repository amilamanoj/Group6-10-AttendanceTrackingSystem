package com.atse.group6.team10.controller;

import com.atse.group6.team10.model.LoginSession;
import com.atse.group6.team10.utils.AuthentificationUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //special pages
        String loginPageURI = request.getContextPath() + "/login.jsp";
        String loginServletURI = request.getContextPath() + "/login";

        String registrationPageURI = request.getContextPath() + "/registration.jsp";
        String registerURI = request.getContextPath() + "/register";

        String logoutPageURI = request.getContextPath() + "/logout.jsp";

        //rest endpoint
        String restEndpointLoginURI = request.getContextPath() + "/rest/login" ;
        boolean restEndpointLoginRequest = request.getRequestURI().equals(restEndpointLoginURI) ;

        Cookie sessionCookie = AuthentificationUtils.getCookie(AuthentificationUtils.SESSION_COOKIE, request);
        LoginSession session = AuthentificationUtils.getSessionFromCookie(sessionCookie);

        boolean registerRequest = request.getRequestURI().equals(registerURI);
        boolean loadRegistrationPage = request.getRequestURI().equals(registrationPageURI);

        //TODO check whether the session is valid or not
        boolean validSession = session != null;
        boolean loginRequest = request.getRequestURI().equals(loginServletURI);
        boolean loadLoginPage = request.getRequestURI().equals(loginPageURI);

        boolean loadLogoutPage = request.getRequestURI().equals(logoutPageURI);

        if (registerRequest || loadRegistrationPage
                || loginRequest || loadLoginPage
                || loadLogoutPage
                || validSession
                || restEndpointLoginRequest) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(loginPageURI +
                    "?redirect=" + request.getRequestURI());
        }
    }


    @Override
    public void destroy() {

    }
}
