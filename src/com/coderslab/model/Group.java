package com.coderslab.model;

public class Group {

    private int id;
    private String name;

    public Group(String name) {
        this.name = name;
    }

    public Group() {}

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

}
