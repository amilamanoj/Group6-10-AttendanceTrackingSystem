package com.atse.group6.team10.resource;

public class AttendanceRest {

    private Long id;
    private int weekId;
    private boolean attended;
    private boolean presented;
    private String date;

    public AttendanceRest(){
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

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
