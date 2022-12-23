package com.example.CovidProject.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AffectedCountries {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("Country")
    public String country;
    @JsonProperty("CountryCode")
    public String countryCode;
    @JsonProperty("Province")
    public String province;
    @JsonProperty("City")
    public String city;
    @JsonProperty("CityCode")
    public String cityCode;
    @JsonProperty("Lat")
    public String lat;
    @JsonProperty("Lon")
    public String lon;
    @JsonProperty("Cases")
    public int cases;
    @JsonProperty("Status")
    public String status;

    public AffectedCountries() {

    }

    public AffectedCountries(String country, String countryCode, String province, String city, String cityCode, String lat, String lon, int cases, String status) {
        this.country = country;
        this.countryCode = countryCode;
        this.province = province;
        this.city = city;
        this.cityCode = cityCode;
        this.lat = lat;
        this.lon = lon;
        this.cases = cases;
        this.status = status;
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

    public Long getId() {
        return id;
    }

    public AffectedCountries(String country) {
        this.country = country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
