package com.atse.group6.team10.controller;

import com.atse.group6.team10.model.Group;
import com.atse.group6.team10.model.Student;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;

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

            String selectedGroupString = req.getParameter("groupNumber");
            Long selectedGroupId = Long.parseLong(selectedGroupString);

            GroupService groupService = new GroupService();
            Group g = groupService.getGroup(selectedGroupId);

            if (s == null) {
                s = new Student(user.getEmail(), user.getUserId(), g);
            }else if(s != null && s.getGroup() == null){
                Ref<Group> groupRef = Ref.create(g);
                s.setGroup(groupRef);
            }
            ObjectifyService.ofy().save().entity(s);

            resp.sendRedirect("registeredGroup.jsp?userId="+user.getUserId());
        }
    }

}
