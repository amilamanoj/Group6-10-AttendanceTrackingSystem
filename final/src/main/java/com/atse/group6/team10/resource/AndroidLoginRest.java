package com.atse.group6.team10.resource;

//Only for gson to json for android
public class AndroidLoginRest {

    private String sessionId;
    private Long userId;
    private boolean student;

    public AndroidLoginRest(){

    }

    public AndroidLoginRest(String sessionId, Long userId, boolean isStudent){
        this.sessionId = sessionId;
        this.userId = userId;
        this.student = isStudent;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }
}
