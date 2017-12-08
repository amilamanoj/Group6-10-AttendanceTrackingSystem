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
    private String id;

    private Date appointmentDate;

    public Group(){

    }

    public String getFormattedAppointmentDate(){
        return groupAppointmentFormat.format(this.appointmentDate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

}
