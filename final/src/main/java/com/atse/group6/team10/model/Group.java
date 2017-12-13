package com.atse.group6.team10.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@JacksonXmlRootElement(localName = "group")
public class Group {

    private static SimpleDateFormat groupAppointmentFormat = new SimpleDateFormat("E HH:mm");

    @Id
    private Long id;

    private String name;

    private Date appointmentDate;

    public Group(){

    }

    public Group (String name, Date appointmentDate){
        this();
        this.name = name;
        this.appointmentDate = appointmentDate;
    }

    public String getFormattedAppointmentDate(){
        return groupAppointmentFormat.format(this.appointmentDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" ");
        sb.append(getFormattedAppointmentDate());
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
