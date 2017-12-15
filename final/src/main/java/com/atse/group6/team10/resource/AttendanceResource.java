package com.atse.group6.team10.resource;

import com.atse.group6.team10.AttendanceApplication;
import com.atse.group6.team10.controller.AttendanceService;
import com.atse.group6.team10.model.Attendance;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class AttendanceResource extends ServerResource {

    private String studentId;
    private String attendanceId;
    private String weekId;

    @Override
    public void doInit() {
        this.studentId = getAttribute(AttendanceApplication.studentIdentifier);
        this.attendanceId = getAttribute(AttendanceApplication.attendanceIdentifier);
        this.weekId = getAttribute(AttendanceApplication.weekNumberIdentifier);
    }


    @Get("json")
    public Attendance getAttendance() {
        AttendanceService service = AttendanceService.getInstance();
        Attendance attendance = service.getAttendanceToken(studentId, Integer.parseInt(weekId));

        return attendance;
    }

    @Post("json")
    public void createAttendance() {
        AttendanceService service = AttendanceService.getInstance();
        service.updateAttendance(Long.valueOf(attendanceId), true);
    }

}
