package com.atse.group6.team10;

import com.atse.group6.team10.resource.AttendanceResource;
import com.atse.group6.team10.resource.AttendancesResource;
import com.atse.group6.team10.resource.QrCodeResource;
import com.atse.group6.team10.resource.UserResource;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class AttendanceApplication extends Application {

    //Identifier for the request parameters
    public static final String attendanceIdentifier = "attendanceId";
    public static final String studentIdentifier = "studentId";
    public static final String userIdentifier = "userId";
    public static final String weekNumberIdentifier = "weekNumber";
    public static final String tokenIdentifier = "token";

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public Restlet createInboundRoot() {
        // Create a router Restlet that routes each call
        Router router = new Router(getContext());

        // Define routes
        router.attach("/users/{" + userIdentifier + "}", UserResource.class);
        router.attach("/token/{" + studentIdentifier + "}/week/{" +weekNumberIdentifier+"}", QrCodeResource.class);
        router.attach("/students/{" + studentIdentifier + "}/attendances", AttendancesResource.class);
        router.attach("/students/attendances/update/{" +tokenIdentifier+"}", AttendanceResource.class);

        return router;
    }
}
