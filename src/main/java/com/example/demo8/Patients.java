package com.example.demo8;

import java.sql.Date;

public class Patients {
    private String fio;
    private Date birthdate;
    private String phone;
    private String address;
    public Patients(String fioPatient, Date birthdatePatient, String phonePatient, String addressPatient) {
        this.fio = fioPatient;
        this.birthdate = birthdatePatient;
        this.phone = phonePatient;
        this.address = addressPatient;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fioPatient) {
        this.fio = fioPatient;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdatePatient) {
        this.birthdate = birthdatePatient;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phonePatient) {
        this.phone = phonePatient;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String addressPatient) {
        this.address = addressPatient;
    }
}
