package com.example.CovidProject.JsonData;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Root {

    @SerializedName("Countries")
    public ArrayList<Countries> countries;

    @Override
    public String toString() {
        return "Root{" +
                "countries=" + countries +
                '}';
    }

    public ArrayList<Countries> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Countries> countries) {
        this.countries = countries;
    }

    public Root(ArrayList<Countries> countries) {
        this.countries = countries;
    }
}
