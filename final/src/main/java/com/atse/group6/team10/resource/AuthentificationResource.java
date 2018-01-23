package com.atse.group6.team10.resource;

import com.atse.group6.team10.controller.service.LoginService;
import com.atse.group6.team10.model.LoginSession;
import com.google.appengine.repackaged.com.google.gson.Gson;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class AuthentificationResource extends ServerResource {

    @Post("json")
    public String login(String json) {
        Gson gson = new Gson();
        CredentialRest credentials = gson.fromJson(json, CredentialRest.class);
        if(credentials.getEmail() == null || credentials.getPassword() == null)
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "Wrong format.");
        LoginService service = LoginService.getInstance();
        LoginSession session = service.login(credentials.getEmail(), credentials.getPassword());
        if (session == null) {
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "Authentification failed, please check username and password.");
        }
        return gson.toJson(session);
    }

}
