package com.example.demo8;

import java.sql.Time;

public class OurServices {

    private String Service;
    private Double CostS;
    public OurServices(String serv, Double costServ) {
        this.Service = serv;
        this.CostS = costServ;
    }

    public String getService() {
        return Service;
    }

    public void setService(String serv) {
        this.Service = serv;
    }

    public Double getCostS() {
        return CostS;
    }

    public void setCostS(Double costServ) {
        this.CostS = costServ;
    }

}
