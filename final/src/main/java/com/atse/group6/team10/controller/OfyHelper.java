package com.atse.group6.team10.controller;

import com.atse.group6.team10.model.*;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OfyHelper implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        // This will be invoked as part of a warmup request, or the first user request if no warmup
        // request.
        ObjectifyService.register(User.class);
        ObjectifyService.register(Tutor.class);
        ObjectifyService.register(LoginSession.class);
        ObjectifyService.register(Student.class);
        ObjectifyService.register(Group.class);
        ObjectifyService.register(Attendance.class);
    }

    public void contextDestroyed(ServletContextEvent event) {
        // App Engine does not currently invoke this method.
    }
}