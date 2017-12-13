package com.atse.group6.team10.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
@JacksonXmlRootElement(localName = "student")
public class Student {

    @Id
    private String id;
    private String email;
    private Ref<Group> group;

    public Student() {

    }

    public Student(String email, String userId) {
        this.email = email;
        this.id = userId;
    }

    public Student(String email, String userId, Group group) {
        this(email, userId);
        this.group = Ref.create(group);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ref<Group> getGroup() {
        return this.group;
    }

    public void setGroup(Ref<Group> group) {
        this.group = group;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
