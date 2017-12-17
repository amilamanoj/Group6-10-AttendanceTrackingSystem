package com.atse.group6.team10.controller;

import com.atse.group6.team10.model.Group;
import com.atse.group6.team10.model.Student;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Ref;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class RegistrationServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();  // Find out who the user is.
        if (user != null) {
            StudentService studentService = StudentService.getInstance();
            Student student = studentService.getStudentForUser(user.getUserId());

            String selectedGroupString = req.getParameter("groupNumber");
            Long selectedGroupId = Long.parseLong(selectedGroupString);

            GroupService groupService = GroupService.getInstance();
            Group group = groupService.getGroup(selectedGroupId);

            if (student == null) {
                student = new Student(user.getEmail(), user.getUserId(), group);
            } else {
                Ref<Group> groupRef = Ref.create(group);
                student.setGroup(groupRef);
            }
            ofy().save().entity(student);

            AttendanceService.getInstance().createAttendanceRecordsForStudent(student);

            resp.sendRedirect("registeredGroup.jsp?userId="+user.getUserId());
        }
    }

}
