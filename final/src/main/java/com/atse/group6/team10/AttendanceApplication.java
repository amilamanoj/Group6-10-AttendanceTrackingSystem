package com.atse.group6.team10;

import com.atse.group6.team10.resource.QrCodeResource;
import com.atse.group6.team10.resource.StudentResource;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class AttendanceApplication extends Application {

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public Restlet createInboundRoot() {
        // Create a router Restlet that routes each call
        Router router = new Router(getContext());

        // Define routes
        router.attach("/students/{student}", StudentResource.class);
        router.attach("/token/{id}", QrCodeResource.class);

        return router;
    }
}
