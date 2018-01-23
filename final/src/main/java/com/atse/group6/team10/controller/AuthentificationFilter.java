package com.atse.group6.team10.controller;

import com.atse.group6.team10.controller.servlet.LoginServlet;
import com.atse.group6.team10.model.LoginSession;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthentificationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String loginPageURI = request.getContextPath() + "/login.jsp";
        String loginServletURI = request.getContextPath() + "/login";
        String registrationPageURI = request.getContextPath() + "/registration.jsp";
        String registerURI = request.getContextPath() + "/register";

        LoginSession session = LoginServlet.getOptionalLoginSession(request);

        boolean registerRequest = request.getRequestURI().equals(registerURI);
        boolean loadRegistrationPage = request.getRequestURI().equals(registrationPageURI);

        //TODO check whether the session is valid or not
        boolean validSession = LoginServlet.getOptionalLoginSession(request) != null;
        boolean loginRequest = request.getRequestURI().equals(loginServletURI);
        boolean loadLoginPage = request.getRequestURI().equals(loginPageURI);

        if (registerRequest || loadRegistrationPage || validSession || loginRequest || loadLoginPage) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(loginPageURI);
        }
    }

    @Override
    public void destroy() {

    }
}
