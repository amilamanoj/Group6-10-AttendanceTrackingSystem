package com.atse.group6.team10.resource;

import com.atse.group6.team10.AttendanceApplication;
import com.atse.group6.team10.controller.AttendanceService;
import com.atse.group6.team10.model.Attendance;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.List;

public class AttendancesResource extends ServerResource {

    private String studentId;

    @Override
    public void doInit() {
        this.studentId = getAttribute(AttendanceApplication.studentIdentifier);
    }

    @Get("json")
    public List<Attendance> getAttendances() {
        AttendanceService service = AttendanceService.getInstance();
        List<Attendance> attendances = service.getAttendanceOfStudent(studentId);
        return attendances;
    }
}
