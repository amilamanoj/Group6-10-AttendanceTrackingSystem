package com.atse.group6.team10;

import com.atse.group6.team10.resource.AttendanceResource;
import com.atse.group6.team10.resource.AttendancesResource;
import com.atse.group6.team10.resource.QrCodeResource;
import com.atse.group6.team10.resource.StudentResource;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class AttendanceApplication extends Application {

    //Identifier for the request parameters
    public static final String attendanceIdentifier = "attendanceId";
    public static final String studentIdentifier = "studentId";

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public Restlet createInboundRoot() {
        // Create a router Restlet that routes each call
        Router router = new Router(getContext());

        // Define routes
        router.attach("/students/{" + studentIdentifier + "}", StudentResource.class);
        router.attach("/token/{id}", QrCodeResource.class);
        router.attach("/students/{" + studentIdentifier + "}/attendances", AttendancesResource.class);
        router.attach("/students/{" + studentIdentifier + "}/attendances/{" +attendanceIdentifier+"}", AttendanceResource.class);

        return router;
    }
}
