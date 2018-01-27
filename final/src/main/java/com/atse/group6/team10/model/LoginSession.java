package com.atse.group6.team10.model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.UUID;

@Entity
public class LoginSession{

    public static String LOGIN_SESSION_KEY = "LOGIN_SESSION_KEY";
    public static String LOGIN_SESSION_USER_KEY = "LOGIN_SESSION_USER_KEY";

    @Id
    private String id;

    private Key<User> user;


    public LoginSession() {

    }

    public LoginSession(Long userId) {
        user = Key.create(User.class, userId);
        id = UUID.randomUUID().toString();
    }

    public Key<User> getUser() {
        return user;
    }

    public void setUser(Key<User> user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
