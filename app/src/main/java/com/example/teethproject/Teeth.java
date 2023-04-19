package com.example.teethproject;

import java.io.Serializable;

public class Teeth implements Serializable {
    private long id;
    private int tId;
    private String name;
    private String date;
    private String note;
    private int status;

    public Teeth (long id, int tId, String name, String date, String note, int status) {
        this.id = id;
        this.tId = tId;
        this.name = name;
        this.date = date;
        this.note = note;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public int gettId(){return tId;}

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public Integer getStatus() {return status;}
}