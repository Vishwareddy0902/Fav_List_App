package com.example.favlistapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {

    private String name;
    private ArrayList<String> lists = new ArrayList<>();

    public Category(String name, ArrayList<String> lists) {
        this.name = name;
        this.lists = lists;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getLists() {
        return lists;
    }
}
