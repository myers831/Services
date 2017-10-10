package com.example.admin.services.model;

/**
 * Created by Admin on 10/9/2017.
 */

public class Car  {

    String type, make, color;
    int year;

    public Car(String type, String make, String color, int year) {
        this.type = type;
        this.make = make;
        this.color = color;
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
