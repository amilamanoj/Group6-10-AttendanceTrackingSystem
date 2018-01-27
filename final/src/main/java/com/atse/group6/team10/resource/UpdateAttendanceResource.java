package com.atse.group6.team10.resource;

import com.atse.group6.team10.beans.AttendanceReqRest;
import com.atse.group6.team10.controller.service.AttendanceService;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.googlecode.objectify.NotFoundException;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class UpdateAttendanceResource extends ServerResource {

    @Override
    public void doInit() {
    }


    @Post("json")
    public String updateAttendance(String json) {
        Gson gson = new Gson();

        AttendanceReqRest attendanceRequest = gson.fromJson(json, AttendanceReqRest.class);
        if(attendanceRequest.getToken() == null)
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "Token missing.");

        AttendanceService service = AttendanceService.getInstance();
        try {
            service.updateAttendance(attendanceRequest.getToken(), attendanceRequest.isAttended(), attendanceRequest.isPresented());
            getResponse().setStatus(Status.SUCCESS_OK, "Attendance successfully recorded");
            return "SUCCESS";
        } catch (NotFoundException e) {
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid token");
            return "Invalid token";
        }
    }

}
