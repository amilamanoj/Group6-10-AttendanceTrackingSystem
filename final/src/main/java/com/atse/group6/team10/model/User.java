package com.atse.group6.team10.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class User {

    @Id
    private Long id;
    @Index
    private String email;
    private byte[] passwordHash;
    private byte[] salt;


    public User() {

    }

    public User(String email, byte[] passwordHash, byte[] salt) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public boolean isStudent(){
        return this instanceof Student;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
