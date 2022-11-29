package com.tris.project_androin.model;

import java.io.Serializable;

public class History implements Serializable {
    private int id;
    private String name;
    private String mean;
    public History(int id, String name, String mean) {
        this.id = id;
        this.name = name;
        this.mean = mean;
    }

    public int getId() {
        return id;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
