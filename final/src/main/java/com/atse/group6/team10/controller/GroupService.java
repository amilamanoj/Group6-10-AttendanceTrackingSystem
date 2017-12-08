package com.atse.group6.team10.controller;

import com.atse.group6.team10.model.Group;

import java.text.SimpleDateFormat;
import java.util.*;

public class GroupService {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("E hh:mm");

    private List<Group> groups {
        {
            groups.add()
        }
    };
    private Map<Integer,List<Integer>> groupTeamsMapping;

    public GroupService(){
        groups = new ArrayList<>();
        groupTeamsMapping = new HashMap<>();

        for(int i = 0; i< 5 ; i ++){
            Group g = new Group();
            g.setId(i);

            String date = "Tuesday 15:00";
            g.setAppointmentDate();
            groups.add(i);
        }

        for(int i = 0; i < groups.size(); i++){
            List<Integer> teams = new ArrayList<>();
            for(int j = 0; j < 10; j++){
                teams.add(j);
            }
            groupTeamsMapping.put(i,teams);
        }
    }

    public List<Integer> getGroups() {
        return groups;
    }

    public void setGroups(List<Integer> groups) {
        this.groups = groups;
    }

    public Map<Integer, List<Integer>> getGroupTeamsMapping() {
        return groupTeamsMapping;
    }

    public void setGroupTeamsMapping(Map<Integer, List<Integer>> groupTeamsMapping) {
        this.groupTeamsMapping = groupTeamsMapping;
    }
}
