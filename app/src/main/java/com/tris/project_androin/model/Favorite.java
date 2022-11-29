package com.tris.project_androin.model;

public class Favorite {
    private int id;
    private String name;
    private String mean;

    public Favorite(int id, String name, String mean) {
        this.id = id;
        this.name = name;
        this.mean = mean;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public int getId() {
        return id;
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
