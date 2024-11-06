package com.example.demo8;

import java.sql.Time;

public class Schedule {
    private String Day_of_week;
    private Time Time_beginning;
    public Schedule(String day, Time timeBeginning) {
        this.Day_of_week = day;
        this.Time_beginning = timeBeginning;
    }

    public String getDay_of_week() {
        return Day_of_week;
    }

    public void setDay_of_week(String day) {
        this.Day_of_week = day;
    }

    public Time getTime_beginning() {
        return Time_beginning;
    }

    public void setTime_beginning(Time timeBeginning) {
        this.Time_beginning = timeBeginning;
    }

}
