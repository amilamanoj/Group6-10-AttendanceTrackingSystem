package com.atse.group6.team10.controller;

import com.atse.group6.team10.model.Group;
import com.googlecode.objectify.ObjectifyService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GroupService {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("E hh:mm");

    private static List<Group> groups;

    public static List<Group> getGroups() {
        groups = ObjectifyService.ofy()
                .load()
                .type(Group.class)
                .list();
        if (groups.isEmpty()) {
            ObjectifyService.ofy().save().entity(new Group("Group1", parseDate("Tuesday 15:00"))).now();
            ObjectifyService.ofy().save().entity(new Group("Group2", parseDate("Tuesday 9:00"))).now();
            ObjectifyService.ofy().save().entity(new Group("Group3", parseDate("Sunday 11:00"))).now();
            ObjectifyService.ofy().save().entity(new Group("Group4", parseDate("Monday 13:00"))).now();
            ObjectifyService.ofy().save().entity(new Group("Group5", parseDate("Friday 23:00"))).now();
            return getGroups();
        }
        return groups;
    }

    private static Date parseDate(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public GroupService() {
        groups = getGroups();
    }

    public Group getGroup(Long groupId) {
        Group foundGroup = null;
        for (Group g : groups) {
            if (g.getId().equals(groupId)) {
                foundGroup = g;
                break;
            }
        }
        return foundGroup;
    }
}
