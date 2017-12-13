package com.atse.group6.team10.controller;

import com.atse.group6.team10.model.Attendance;
import com.atse.group6.team10.model.Student;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.LoadResult;
import com.googlecode.objectify.ObjectifyService;

import java.util.List;

public class AttendanceService {

    public List<Attendance> getAttendanceOfStudent(String studentId){
        //TODO: how to get all attendances of a student
        List<Attendance> loadResult = ObjectifyService.ofy().load().type(Attendance.class).filter("Id ==", studentId).list();
        return loadResult;
    }

    public void createAttendance(Attendance a){
        Key<Attendance> attendanceKey = ObjectifyService.ofy().save().entity(a).now();
    }

}
