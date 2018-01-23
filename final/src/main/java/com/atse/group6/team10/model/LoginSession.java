package com.atse.group6.team10.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
public class LoginSession implements Serializable {

    public static String LOGIN_SESSION_KEY = "LOGIN_SESSION_KEY";
    public static String LOGIN_SESSION_USER_KEY = "LOGIN_SESSION_USER_KEY";

    @Id
    private String id;

    private Key<User> user;
    private Date startDate;


    public LoginSession() {

    }

    public LoginSession(Long userId) {
        user = Key.create(User.class, userId);
        id = UUID.randomUUID().toString();
        startDate = new Date(System.currentTimeMillis());
    }

    public Key<User> getUser() {
        return user;
    }

    public void setUser(Key<User> user) {
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoginSession " +
                "[" +
                "id=" + id + ", " +
                "user=" + user + ", " +
                "startDate=" + startDate +
                "]";
    }
}
