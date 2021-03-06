package com.atse.group6.team10;

import com.atse.group6.team10.resource.*;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class AttendanceApplication extends Application {

    //Identifier for the request parameters
    public static final String studentIdentifier = "studentId";
    public static final String userIdentifier = "userId";
    public static final String weekNumberIdentifier = "weekNumber";

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public Restlet createInboundRoot() {
        // Create a router Restlet that routes each call
        Router router = new Router(getContext());

        // Define routes
        //REST login
        router.attach("/login", AuthenticationResource.class);
        // get QR code token for a given student for a given week
        router.attach("/token/{" + studentIdentifier + "}/week/{" +weekNumberIdentifier+"}", AttendanceQrCodeResource.class);
        // get all attendance records of a student
        router.attach("/students/{" + studentIdentifier + "}/attendances", AttendancesResource.class);

        // tutor login token for Pi
        router.attach("/tutor/qrCode", TutorQrCodeResource.class);
        // record attendance using a given token
        router.attach("/students/attendances/update", UpdateAttendanceResource.class);

        return router;
    }
}
