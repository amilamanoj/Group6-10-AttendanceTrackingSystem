package com.atse.group6.team10.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@JacksonXmlRootElement(localName = "attendance")
public class Attendance {

    @Id
    private Long id;
    @Index
    private String studentId;
    private long groupId;
    @Index
    private int weekId;
    private boolean presented;

    public Attendance(){

    }

    public Attendance(String studentId, long groupId, int weekId, boolean presented) {
        this.studentId = studentId;
        this.groupId = groupId;
        this.weekId = weekId;
        this.presented = presented;
    }

    public boolean isPresented() {
        return presented;
    }

    public void setPresented(boolean presented) {
        this.presented = presented;
    }

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
