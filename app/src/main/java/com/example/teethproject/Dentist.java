package com.example.teethproject;

import java.io.Serializable;

public class Dentist implements Serializable {
    private long id;
    private String name;
    private String clinic;
    private String phone;

    public Dentist (long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.clinic = address;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClinic() {
        return clinic;
    }

    public String getPhone() {
        return phone;
    }

}