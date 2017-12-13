package com.atse.group6.team10.model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Attendance {

    @Id
    private Long id;
    @Index
    private Key<Student> student;
    private Key<Group> group;
    private long weekId;
    private boolean presented;

    public Attendance(){

    }

    public Attendance(Key<Student> studentKey, Key<Group> groupKey, long weekId, boolean presented){
        this.student = studentKey;
        this.group = groupKey;
        this.weekId = weekId;
        this.presented = presented;
    }

    public boolean isPresented() {
        return presented;
    }

    public void setPresented(boolean presented) {
        this.presented = presented;
    }

    public long getWeekId() {
        return weekId;
    }

    public void setWeekId(long weekId) {
        this.weekId = weekId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Key<Student> getStudent() {
        return student;
    }

    public void setStudent(Key<Student> student) {
        this.student = student;
    }

    public Key<Group> getGroup() {
        return group;
    }

    public void setGroup(Key<Group> group) {
        this.group = group;
    }
}
