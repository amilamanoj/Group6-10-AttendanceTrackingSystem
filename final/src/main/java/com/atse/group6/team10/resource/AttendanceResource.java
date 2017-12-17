package com.atse.group6.team10.resource;

import com.atse.group6.team10.AttendanceApplication;
import com.atse.group6.team10.controller.AttendanceService;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class AttendanceResource extends ServerResource {

    private String studentId;
    private String token;
    private String weekId;

    @Override
    public void doInit() {
        this.studentId = getAttribute(AttendanceApplication.studentIdentifier);
        this.token = getAttribute(AttendanceApplication.tokenIdentifier);
        this.weekId = getAttribute(AttendanceApplication.weekNumberIdentifier);
    }


    @Post("json")
    public void updateAttendance() {
        AttendanceService service = AttendanceService.getInstance();
        service.updateAttendance(token, true);
    }

}
