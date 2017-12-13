package com.atse.group6.team10.resource;

import com.atse.group6.team10.AttendanceApplication;
import com.atse.group6.team10.controller.StudentService;
import com.atse.group6.team10.model.Student;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;


public class StudentResource extends ServerResource {

    private String studentId;

    @Override
    public void doInit() {
        this.studentId = getAttribute(AttendanceApplication.studentIdentifier);
    }

    @Get("json")
    public Student getStudent() {

        //studentId
        Student s = new StudentService().getStudentForUser(studentId);
        return s;
    }

}