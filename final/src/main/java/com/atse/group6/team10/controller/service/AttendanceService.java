package com.atse.group6.team10.controller.service;

import com.atse.group6.team10.model.Attendance;
import com.atse.group6.team10.model.Student;
import com.googlecode.objectify.NotFoundException;

import java.util.List;
import java.util.UUID;

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

    public List<Attendance> getAttendanceOfStudent(Long studentId) {
        List<Attendance> loadResult = ofy().load().type(Attendance.class).filter("studentId", studentId).list();
        return loadResult;
    }

    public Attendance getAttendanceToken(Long studentId, int weekNumber) {
        List<Attendance> attendances = ofy().load().type(Attendance.class).filter("studentId", studentId)
                .filter("weekId", weekNumber).list();
        if (attendances.isEmpty()) {
            throw new NotFoundException();
        }
        return attendances.get(0);
    }

    public void updateAttendance(String token, boolean attended) {
        List<Attendance> attendances = ofy().load().type(Attendance.class).filter("token", token).list();
        if (attendances.isEmpty()) {
            throw new NotFoundException();
        }
        Attendance attendance = attendances.get(0);
        attendance.setAttended(attended);
        ofy().save().entity(attendance).now();
    }

    public void createAttendanceRecordsForStudent(Student student) {
        for (int week = 1; week <= NUM_OF_WEEKS; week++) {
            Attendance attendance = new Attendance(student.getId(), student.getGroup().get().getId(), week, false);
            String token = UUID.randomUUID().toString();
            attendance.setToken(token);
            ofy().save().entity(attendance).now();
        }
    }

}
