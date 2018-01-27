package com.atse.group6.team10.resource;

import com.atse.group6.team10.controller.service.QRCodeService;
import com.atse.group6.team10.utils.AuthentificationUtils;
import org.restlet.data.Cookie;
import org.restlet.data.MediaType;
import org.restlet.representation.ByteArrayRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.io.IOException;

public class TutorQrCodeResource extends ServerResource {

    @Get("image/jpeg")
    public Representation retrieve() throws IOException {
        Cookie cookie = getRequest().getCookies().getFirst(AuthentificationUtils.SESSION_COOKIE);
        String token = cookie.getName() + "=" + cookie.getValue();
        byte[] qrCodeContent = QRCodeService.getInstance().getQRCode(token);
        return new ByteArrayRepresentation(qrCodeContent, MediaType.IMAGE_JPEG);
    }
}
