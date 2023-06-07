package com.example.teethproject;

import java.io.Serializable;

public class Clinic implements Serializable {
    private long id;
    private String name;
    private String address;
    private String phone;

    public Clinic (long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

}