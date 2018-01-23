package com.atse.group6.team10.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class Tutor extends User {

    private Ref<Group> group;

    public Tutor() {

    }

    public Tutor(String email, byte[] passwordHash, byte[] salt) {
        super(email, passwordHash, salt);
    }

    public Ref<Group> getGroup() {
        return this.group;
    }

    public void setGroup(Ref<Group> group) {
        this.group = group;
    }

}
