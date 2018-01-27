package com.atse.group6.team10.resource;

import com.atse.group6.team10.AttendanceApplication;
import com.atse.group6.team10.controller.service.AttendanceService;
import com.atse.group6.team10.controller.service.QRCodeService;
import com.atse.group6.team10.model.Attendance;
import com.googlecode.objectify.NotFoundException;
import org.restlet.data.MediaType;
import org.restlet.representation.ByteArrayRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.io.*;


public class AttendanceQrCodeResource extends ServerResource {

    private Long studentId;
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
        byte[] qrCodeContent = QRCodeService.getInstance().getQRCode(token);
        return new ByteArrayRepresentation(qrCodeContent, MediaType.IMAGE_JPEG);
    }

    @Override
    public void doInit() {
        String urlParameter =getAttribute(AttendanceApplication.studentIdentifier);
        this.studentId = Long.parseLong(urlParameter);
        this.weekId = getAttribute(AttendanceApplication.weekNumberIdentifier);
    }


}