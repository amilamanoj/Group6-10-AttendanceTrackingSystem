package com.atse.group6.team10.controller;

import com.atse.group6.team10.model.Student;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();  // Find out who the user is.
        if (user != null) {
            StudentService studentService = new StudentService();
            Student s = studentService.getStudentForUser(user.getUserId());
            int selectedGroup = Integer.parseInt(req.getParameter("groupNumber"));
            int selectedTeam = Integer.parseInt(req.getParameter("teamNumber"));
            if (s == null) {
                s = new Student(user.getEmail(), user.getUserId(), selectedGroup, selectedTeam);
            }else{
                s.setGroup(selectedGroup);
                s.setTeamNumber(selectedTeam);
            }
            ObjectifyService.ofy().save().entity(s);

            resp.sendRedirect("registeredGroup.jsp?userId="+user.getUserId());
        }
    }

}
