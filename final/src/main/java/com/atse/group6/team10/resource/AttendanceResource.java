package com.atse.group6.team10.resource;

import com.atse.group6.team10.AttendanceApplication;
import com.atse.group6.team10.controller.AttendanceService;
import org.restlet.data.Status;
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
    public String updateAttendance() {
        AttendanceService service = AttendanceService.getInstance();
        try {
            service.updateAttendance(token, true);
            getResponse().setStatus(Status.SUCCESS_OK, "There is a conflict");
            return "SUCCESS";
        } catch (Exception e) {
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "There is a conflict");
            return "Invalid token";
        }
    }

}
