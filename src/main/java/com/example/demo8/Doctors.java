package com.example.demo8;

import java.sql.Date;

public class Doctors {

    private String fio;
    private Date birthdate;
    private String phone;
    private String address;
    public Doctors(String fioDoctor, Date birthdateDoctor, String phoneDoctor, String addressDoctor) {
        this.fio = fioDoctor;
        this.birthdate = birthdateDoctor;
        this.phone = phoneDoctor;
        this.address = addressDoctor;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fioDoctor) {
        this.fio = fioDoctor;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setTime_beginning(Date birthdateDoctor) {
        this.birthdate = birthdateDoctor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phoneDoctor) {
        this.phone = phoneDoctor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String addressDoctor) {
        this.address = addressDoctor;
    }
}
