package com.atse.group6.team10.resource;

import com.atse.group6.team10.AttendanceApplication;
import com.atse.group6.team10.beans.AttendanceRest;
import com.atse.group6.team10.controller.service.AttendanceService;
import com.atse.group6.team10.controller.service.UserService;
import com.atse.group6.team10.model.Attendance;
import com.atse.group6.team10.model.Group;
import com.atse.group6.team10.model.Student;
import com.atse.group6.team10.model.User;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AttendancesResource extends ServerResource {

    private Long studentId;

    @Override
    public void doInit() {
        String urlParameter =getAttribute(AttendanceApplication.studentIdentifier);
        this.studentId = Long.parseLong(urlParameter);
    }

    @Get("json")
    public List<AttendanceRest> getAttendances() {
        List<AttendanceRest> attendanceRestList = new ArrayList<>();

        AttendanceService service = AttendanceService.getInstance();
        List<Attendance> attendances = service.getAttendanceOfStudent(studentId);

        User user = UserService.getInstance().getUserForId(studentId);

        if (user.isStudent()) {
            Student s = (Student) user;
            Group group = s.getGroup().get();
            Date firstDate = group.getAppointmentDate();
            Calendar calendar = Calendar.getInstance();

            for (Attendance attendance : attendances) {
                AttendanceRest attendanceRest = new AttendanceRest();
                attendanceRest.setId(attendance.getId());
                attendanceRest.setWeekId(attendance.getWeekId());
                attendanceRest.setAttended(attendance.isAttended());
                attendanceRest.setPresented(attendance.isPresented());
                calendar.setTime(firstDate);
                calendar.add(Calendar.DAY_OF_YEAR, attendance.getWeekId() * 7);
                attendanceRest.setDate(new SimpleDateFormat().format(calendar.getTime()));
                attendanceRestList.add(attendanceRest);
            }
        }

        return attendanceRestList;
    }
}
