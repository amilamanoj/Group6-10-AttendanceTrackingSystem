package com.atse.group6.team10.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
@JacksonXmlRootElement(localName = "student")
public class Student {

    @Id
    private String id;

    private String email;
    private Group group;
    private int teamNumber;

    public Student(){

    }

    public Student(String email, String userId) {
        this.email = email;
        this.id = userId;
    }

    public Student(String email, String userId, Group group, int teamNumber) {
        this(email, userId);
        this.group = group;
        this.teamNumber = teamNumber;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getTeamNumber() {
        return this.teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
