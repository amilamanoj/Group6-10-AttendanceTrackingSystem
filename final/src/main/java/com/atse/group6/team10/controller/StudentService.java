package com.atse.group6.team10.controller;

import com.atse.group6.team10.model.Student;
import com.googlecode.objectify.LoadResult;
import com.googlecode.objectify.ObjectifyService;

public class StudentService {

    public Student getStudentForUser(String userId) {
        LoadResult<Student> loadResult = ObjectifyService.ofy().load().type(Student.class).id(userId);
        return loadResult.now();
    }

}
