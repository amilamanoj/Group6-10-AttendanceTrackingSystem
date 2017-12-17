package com.atse.group6.team10.resource;

import com.atse.group6.team10.AttendanceApplication;
import com.atse.group6.team10.controller.AttendanceService;
import com.atse.group6.team10.model.Attendance;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.googlecode.objectify.NotFoundException;
import org.restlet.data.MediaType;
import org.restlet.representation.ByteArrayRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.io.*;
import java.net.URL;


public class QrCodeResource extends ServerResource {

    public static final String CHART_API_CALL = "http://chart.apis.google.com/chart?cht=qr&chs=300x300&chl=";
    private String studentId;
    private String weekId;

    @Get("image/jpeg")
    public Representation retrieve() throws IOException {
        if (studentId == null) {
            return new StringRepresentation("Student id required");
        }
        if (weekId == null) {
            return new StringRepresentation(("Week id required"));
        }
        int week = 0;
        try {
            week = Integer.parseInt(weekId);
        } catch (Exception ignored) {
        }
        if (week <= 0 || week > 13) {
            return new StringRepresentation(("Invalid week"));
        }
        Attendance attendance = null;
        try {
            attendance = AttendanceService.getInstance().getAttendanceToken(studentId, week);
        } catch (NotFoundException ignored) {
        }
        if (attendance == null) {
            return new StringRepresentation(("Token not found"));
        }
        String token = attendance.getToken();
        URL url = new URL(CHART_API_CALL + token);
        URLFetchService urlService = URLFetchServiceFactory.getURLFetchService();
        byte[] content = urlService.fetch(url).getContent();
        return new ByteArrayRepresentation(content, MediaType.IMAGE_JPEG);
    }

    @Override
    public void doInit() {
        this.studentId = getAttribute(AttendanceApplication.studentIdentifier);
        this.weekId = getAttribute(AttendanceApplication.weekNumberIdentifier);
    }


}