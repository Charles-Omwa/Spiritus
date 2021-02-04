package com.example.spiritus;

public class Hero {

    private String title, description, date;

    public Hero(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;

    }

    public String getName() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

}
