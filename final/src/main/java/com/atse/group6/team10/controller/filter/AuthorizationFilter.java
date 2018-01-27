package com.atse.group6.team10.controller.filter;

import com.atse.group6.team10.controller.service.UserService;
import com.atse.group6.team10.model.LoginSession;
import com.atse.group6.team10.model.Tutor;
import com.atse.group6.team10.model.User;
import com.atse.group6.team10.utils.AuthentificationUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        // check if the session is valid
        LoginSession session = getSession(request);
        User user = null;
        if (session != null) {
            long userId = session.getUser().getId();
            user = UserService.getInstance().getUserForId(userId);
        }

        if (user == null  // only need to do authorization of logged sessions
                || isAuthorized(request, user)) {

            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not authorized to perform this action");
        }
    }

    private boolean isAuthorized(HttpServletRequest request, User user) {
        boolean isTutor = false;
        if (user instanceof Tutor) {
            isTutor = true;
        }
        boolean attendanceUpdateReq = request.getRequestURI().equals(
                request.getContextPath() + "/rest/students/attendances/update");
        boolean tutorQrCodeReq = request.getRequestURI().equals(
                request.getContextPath() + "/rest//tutor/qrCode");

        if (attendanceUpdateReq || tutorQrCodeReq) { // tutor credentials are needed to update attendance (via Pi)
            return isTutor;
        } else {
            return  true;
        }
    }

    private LoginSession getSession(HttpServletRequest request) {
        Cookie sessionCookie = AuthentificationUtils.getCookie(AuthentificationUtils.SESSION_COOKIE, request);
        return AuthentificationUtils.getSessionFromCookie(sessionCookie);
    }


    @Override
    public void destroy() {
    }
}
