package com.atse.group6.team10.controller;

import com.atse.group6.team10.model.Student;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class StudentService {

    private static StudentService instance;

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    private StudentService() {
    }

    public Student getStudentForUser(String userId) {
        LoadResult<Student> loadResult = ofy().load().type(Student.class).id(userId);
        return loadResult.now();
    }


}
