package com.example.CovidProject.JsonData;

import com.google.gson.annotations.SerializedName;

public class Countries {

    @SerializedName("Country")
    public String country;

    @SerializedName("TotalConfirmed")
    public int totalConfirmed;

    @SerializedName("TotalDeaths")
    public int totalDeaths;

    @SerializedName("TotalRecovered")
    public int totalRecovered;

    @SerializedName("Date")
    public String date;

    public Countries(String country, int totalConfirmed, int totalDeaths, int totalRecovered, String date) {
        this.country = country;
        this.totalConfirmed = totalConfirmed;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Countries{" +
                "country='" + country + '\'' +
                ", totalConfirmed=" + totalConfirmed +
                ", totalDeaths=" + totalDeaths +
                ", totalRecovered=" + totalRecovered +
                ", date='" + date + '\'' +
                '}';
    }
}
