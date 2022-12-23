package com.example.CovidProject.JsonData;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("Country")
    public String country;
    @SerializedName("CountryCode")
    public String countryCode;
    @SerializedName("Province")
    public String province;
    @SerializedName("City")
    public String city;
    @SerializedName("CityCode")
    public String cityCode;
    @SerializedName("Lat")
    public String lat;
    @SerializedName("Lon")
    public String lon;
    @SerializedName("Cases")
    public int cases;
    @SerializedName("Status")
    public String status;
    @SerializedName("Date")
    public String date;


    public Country(String country, String countryCode, String province, String city, String cityCode, String lat, String lon, int cases, String status, String date) {
        this.country = country;
        this.countryCode = countryCode;
        this.province = province;
        this.city = city;
        this.cityCode = cityCode;
        this.lat = lat;
        this.lon = lon;
        this.cases = cases;
        this.status = status;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Result{" +
                "country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", cases=" + cases +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }
}
