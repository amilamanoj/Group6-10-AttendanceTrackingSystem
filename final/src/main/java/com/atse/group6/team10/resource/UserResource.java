package com.atse.group6.team10.resource;

import com.atse.group6.team10.AttendanceApplication;
import com.atse.group6.team10.controller.service.UserService;
import com.atse.group6.team10.model.Student;
import com.atse.group6.team10.model.User;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;


public class UserResource extends ServerResource {

    private Long userId;

    @Override
    public void doInit() {
        String urlParameter = getAttribute(AttendanceApplication.userIdentifier);
        this.userId = Long.parseLong(urlParameter);
    }

    @Get("json")
    public User getUser() {
        User s = UserService.getInstance().getUserForId(userId);
        return s;
    }

}