package com.atse.group6.team10.resource;

import com.atse.group6.team10.beans.AndroidLoginRest;
import com.atse.group6.team10.beans.CredentialRest;
import com.atse.group6.team10.controller.service.ConfigurationService;
import com.atse.group6.team10.controller.service.LoginService;
import com.atse.group6.team10.controller.service.UserService;
import com.atse.group6.team10.model.LoginSession;
import com.atse.group6.team10.model.User;
import com.atse.group6.team10.utils.AuthentificationUtils;
import com.google.appengine.repackaged.com.google.gson.Gson;
import org.restlet.data.CookieSetting;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class AuthenticationResource extends ServerResource {

    @Post("json")
    public String login(String json) {
        Gson gson = new Gson();
        CredentialRest credentials = gson.fromJson(json, CredentialRest.class);
        if(credentials.getEmail() == null || credentials.getPassword() == null)
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "Wrong format.");
        LoginService service = LoginService.getInstance();
        LoginSession session = service.login(credentials.getEmail(), credentials.getPassword());
        User user = UserService.getInstance().getUserForId(session.getUser().getId());
        AndroidLoginRest response = null;
        if (session == null || user == null) {
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "Authentification failed, please check username and password.");
        }else {
            response = new AndroidLoginRest(session.getId(), user.getId(), user.isStudent());
            CookieSetting sessionCookie = new CookieSetting(0, AuthentificationUtils.SESSION_COOKIE,session.getId());
            ConfigurationService configService = ConfigurationService.getInstance();
            int cookieLifetime = configService.getConfig().getInt("cookie.lifetime");
            sessionCookie.setMaxAge(cookieLifetime);
            getResponse().getCookieSettings().add(sessionCookie);
        }

        return gson.toJson(response);
    }

}
