package com.atse.group6.team10.controller.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.atse.group6.team10.model.Group;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GroupService {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private List<Group> groups;
    private static GroupService instance;

    public static GroupService getInstance() {
        if (instance == null) {
            instance = new GroupService();
        }
        return instance;
    }

    private GroupService() {
    }

    public List<Group> getGroups() {
        groups = ofy()
                .load()
                .type(Group.class)
                .list();
        if (groups.isEmpty()) {
            createGroups();
            return getGroups();
        }
        return groups;
    }

    private void createGroups() {
        // delete any old data
        Objectify ofy = ObjectifyService.ofy();
        List<Key<Group>> keys = ofy.load().type(Group.class).keys().list();
        ofy.delete().keys(keys).now();
        // add new data
        ofy().save().entity(new Group("Group1", parseDate("18/12/2017 09:00"))).now();
        ofy().save().entity(new Group("Group2", parseDate("19/12/2017 10:00"))).now();
        ofy().save().entity(new Group("Group3", parseDate("20/12/2017 14:00"))).now();
        ofy().save().entity(new Group("Group4", parseDate("21/12/2017 11:00"))).now();
        ofy().save().entity(new Group("Group5", parseDate("22/12/2017 09:30"))).now();
    }

    private Date parseDate(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
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
