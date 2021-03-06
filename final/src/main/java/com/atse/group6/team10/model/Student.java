package com.atse.group6.team10.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Subclass;

@Subclass
@JacksonXmlRootElement(localName = "student")
public class Student extends User {


    private Ref<Group> group;

    public Student() {

    }

    public Student(String email, byte[] passwordHash, byte[] salt) {
        super(email, passwordHash, salt);
    }

    public Ref<Group> getGroup() {
        return this.group;
    }

    public void setGroup(Ref<Group> group) {
        this.group = group;
    }

}
