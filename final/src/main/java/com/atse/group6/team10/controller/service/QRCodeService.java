package com.atse.group6.team10.controller.service;

import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

import java.io.IOException;
import java.net.URL;

public class QRCodeService {

    public static final String CHART_API_CALL = "http://chart.apis.google.com/chart?cht=qr&chs=300x300&chl=";

    private static QRCodeService service;

    public static QRCodeService getInstance() {
        if (service == null)
            service = new QRCodeService();
        return service;
    }

    private QRCodeService (){

    }


    public byte[] getQRCode(String content) throws IOException {
        URL url = new URL(CHART_API_CALL + content);
        URLFetchService urlService = URLFetchServiceFactory.getURLFetchService();
        return urlService.fetch(url).getContent();
    }

}
