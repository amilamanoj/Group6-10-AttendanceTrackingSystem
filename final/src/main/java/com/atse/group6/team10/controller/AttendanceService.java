package com.atse.group6.team10.controller;

import com.atse.group6.team10.model.Attendance;
import com.atse.group6.team10.model.Student;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class AttendanceService {

    private static final int NUM_OF_WEEKS = 13;
    private static AttendanceService instance;

    public static AttendanceService getInstance() {
        if (instance == null) {
            instance = new AttendanceService();
        }
        return instance;
    }

    private AttendanceService() {
    }

    public List<Attendance> getAttendanceOfStudent(String studentId) {
        List<Attendance> loadResult = ofy().load().type(Attendance.class).filter("studentId", studentId).list();
        return loadResult;
    }

    public Attendance getAttendanceToken(String studentId, int weekNumber) {
        List<Attendance> attendances = ofy().load().type(Attendance.class).filter("studentId", studentId)
                .filter("weekId", weekNumber).list();
        return attendances.get(0);
    }

    public void updateAttendance(Long attendanceId, boolean present) {
        Attendance attendance = ofy().load().type(Attendance.class).id(attendanceId).now();
        attendance.setPresented(present);
        ofy().save().entity(attendance).now();
    }

    public void createAttendanceRecordsForStudent(Student student) {
        for (int week = 1; week <= NUM_OF_WEEKS; week++) {
            Attendance attendance = new Attendance(student.getId(), student.getGroup().get().getId(), week, false);
            ofy().save().entity(attendance).now();
        }
    }

}
