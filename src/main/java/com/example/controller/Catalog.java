package com.example.controller;

public class Catalog {

    public String name;
    public String desc;
    public String rating;
    public Catalog() {

    }

    public Catalog(String name, String desc, String rating) {
        this.name = name;
        this.desc = desc;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
