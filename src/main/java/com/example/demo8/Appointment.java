package com.example.demo8;

import java.sql.Date;
import java.sql.Time;

public class Appointment {

    private String doctor;
    private String Day_of_weekAppointment;
    private Time Time_appointment;
    private String nameService;
    private Double costAppointment;

    public Appointment(String doctorA, String Day_of_weekA, Time Time_appointmentA, String nameServiceA, Double costA) {
        this.doctor = doctorA;
        this.Day_of_weekAppointment = Day_of_weekA;
        this.Time_appointment = Time_appointmentA;
        this.nameService = nameServiceA;
        this.costAppointment = costA;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String fioDoctor) {
        this.doctor = fioDoctor;
    }


    public String getDay_of_weekAppointment() {
        return Day_of_weekAppointment;
    }

    public void setDay_of_weekAppointment(String dayA) {
        this.Day_of_weekAppointment = dayA;
    }

    public Time getTime_appointment() {
        return Time_appointment;
    }

    public void setTime_appointment(Time timeA) {
        this.Time_appointment = timeA;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameSA) {
        this.nameService = nameSA;
    }

    public Double getCostAppointment() {
        return costAppointment;
    }

    public void setCostAppointment(Double costSA) {
        this.costAppointment = costSA;
    }
}
