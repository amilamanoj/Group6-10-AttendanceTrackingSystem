package com.atse.group6.team10.resource;

import com.atse.group6.team10.AttendanceApplication;
import com.atse.group6.team10.controller.AttendanceService;
import com.atse.group6.team10.model.Attendance;
import com.atse.group6.team10.model.Student;
import com.googlecode.objectify.Key;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.List;

public class AttendanceResource extends ServerResource {

    private String studentId;
    private String attendanceId;

    @Override
    public void doInit() {
        this.studentId = getAttribute(AttendanceApplication.studentIdentifier);
        this.attendanceId = getAttribute(AttendanceApplication.attendanceIdentifier);
    }


    @Get("json")
    public Attendance getAttendance() {
        //attendanceId
        AttendanceService service = new AttendanceService();
        List<Attendance> attendances = service.getAttendanceOfStudent(studentId);
        Attendance attendance = null;

        for (Attendance a : attendances) {
            if (a.getId().equals(attendanceId)) {
                attendance = a;
                break;
            }
        }
        return attendance;
    }

    @Post("json")
    public void createAttendance(Attendance attendance) {
        //attendanceId
        AttendanceService service = new AttendanceService();
        attendance.setStudent(Key.create(Student.class, studentId));
        service.createAttendance(attendance);
    }

}
