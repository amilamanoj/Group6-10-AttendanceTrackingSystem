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
    private Long studentId;
    private long groupId;
    @Index
    private int weekId;
    private boolean attended;
    private boolean presented;
    @Index
    private String token;

    public Attendance(){

    }

    public Attendance(Long studentId, long groupId, int weekId, boolean presented) {
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }
}
