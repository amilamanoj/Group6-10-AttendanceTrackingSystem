package com.atse.group6.team10.controller.servlet;

import com.atse.group6.team10.controller.service.AttendanceService;
import com.atse.group6.team10.controller.service.GroupService;
import com.atse.group6.team10.controller.service.UserService;
import com.atse.group6.team10.model.Group;
import com.atse.group6.team10.model.LoginSession;
import com.atse.group6.team10.model.Student;
import com.atse.group6.team10.model.User;
import com.atse.group6.team10.utils.AuthentificationUtils;
import com.googlecode.objectify.Ref;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class GroupRegistrationServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String selectedGroupString = req.getParameter("groupNumber");

        UserService userService = UserService.getInstance();
        LoginSession session = AuthentificationUtils.getOptionalLoginSession(req);
        Long userId = session.getUser().getId();
        User user = userService.getUserForId(userId);

        if (user != null) {

            Long selectedGroupId = Long.parseLong(selectedGroupString);

            GroupService groupService = GroupService.getInstance();
            Group group = groupService.getGroup(selectedGroupId);

            if (user instanceof Student) {
                Student s = (Student) user;
                Ref<Group> groupRef = Ref.create(group);
                s.setGroup(groupRef);
                ofy().save().entity(user);
                AttendanceService.getInstance().createAttendanceRecordsForStudent(s);
                resp.sendRedirect("registeredGroup.jsp?userId=" + user.getId());
            }
        }
    }

}
