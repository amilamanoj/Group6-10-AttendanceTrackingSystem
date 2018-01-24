package com.atse.group6.team10.controller.service;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConfigurationService {

    private static String ssConfigKey = "semester.ss.start";
    private static String wsConfigKey = "semester.ws.start";


    private Configuration config;

    private static ConfigurationService service;

    public static ConfigurationService getInstance() {
        if (service == null)
            service = new ConfigurationService();
        return service;
    }

    private ConfigurationService (){
        refreshConfig();
    }

    public Configuration getConfig(){
        return config;
    }

    public void refreshConfig(){
        Configurations configs = new Configurations();
        try{
            config = configs.properties(new File("./config.properties"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCurrentWeek(){
        int currentWeek = -1;

        SimpleDateFormat fullFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat weekFormat = new SimpleDateFormat("ww");
        String ssStart = (String) config.getProperty(ssConfigKey);
        String wsStart = (String) config.getProperty(wsConfigKey);
        int currentYear = getCurrentYear();
        int lastYear = getLastYear();

        Date currentDate = new Date(System.currentTimeMillis());

        String currentWeekString;

        try {
            Date startSSCurrentYear = fullFormat.parse(ssStart + "." + currentYear);
            Date startWSCurrentYear = fullFormat.parse(wsStart + "." + currentYear);
            Date startWSLastYear = fullFormat.parse(wsStart + "." + lastYear);
            if(startWSCurrentYear.before(currentDate)){
                //ws of current year
                Long difference = currentDate.getTime()- startWSCurrentYear.getTime();
                currentWeekString = weekFormat.format(new Date(difference));
            }else if(startSSCurrentYear.before(currentDate)){
                //current ss
                Long difference = currentDate.getTime()- startSSCurrentYear.getTime();
                currentWeekString = weekFormat.format(new Date(difference));
            }else{
                //last ws
                Long difference = currentDate.getTime()- startWSLastYear.getTime();
                currentWeekString = weekFormat.format(new Date(difference));
            }
            currentWeek = Integer.parseInt(currentWeekString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return currentWeek;
    }

    private int getLastYear(){
        Calendar prevYear = Calendar.getInstance();
        prevYear.add(Calendar.YEAR, -1);
        return prevYear.get(Calendar.YEAR);
    }

    private int getCurrentYear(){
        Calendar currentYear = Calendar.getInstance();
        return currentYear.get(Calendar.YEAR);
    }


}
