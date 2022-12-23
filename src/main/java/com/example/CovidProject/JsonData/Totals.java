package com.example.CovidProject.JsonData;

import com.google.gson.annotations.SerializedName;

public class Totals {
    @SerializedName("TotalConfirmed")
    public int totalConfirmed;
    @SerializedName("TotalDeaths")
    public int totalDeaths;
    @SerializedName("TotalRecovered")
    public int totalRecovered;


    public Totals(int totalConfirmed, int totalDeaths, int totalRecovered) {
        this.totalConfirmed = totalConfirmed;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
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

    @Override
    public String toString() {
        return "Root{" +
                "totalConfirmed=" + totalConfirmed +
                ", totalDeaths=" + totalDeaths +
                ", totalRecovered=" + totalRecovered +
                '}';
    }
}
