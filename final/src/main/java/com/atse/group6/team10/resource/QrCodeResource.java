package com.atse.group6.team10.resource;

import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import org.restlet.data.MediaType;
import org.restlet.representation.ByteArrayRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.io.*;
import java.net.URL;


public class QrCodeResource extends ServerResource {

    public static final String CHART_API_CALL = "http://chart.apis.google.com/chart?cht=qr&chs=300x300&chl=";
    private String studentId;

    @Get("image/jpeg")
    public Representation retrieve() throws IOException {
        URLFetchService urlService = URLFetchServiceFactory.getURLFetchService();
        URL url = new URL(CHART_API_CALL + studentId);
        byte[] content = urlService.fetch(url).getContent();
        return new ByteArrayRepresentation(content, MediaType.IMAGE_JPEG);
    }

    @Override
    public void doInit() {
        this.studentId = getAttribute("id");
    }


}