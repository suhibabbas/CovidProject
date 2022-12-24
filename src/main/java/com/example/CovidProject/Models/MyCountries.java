package com.example.CovidProject.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class MyCountries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long country_id;

    @Column(columnDefinition = "TEXT")
    private String name;
    @Column(columnDefinition = "TEXT")
    private String date;

    private String flag;

    public MyCountries(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public MyCountries() {

    }

    public Long getCountry_id() {
        return country_id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


//    @ManyToMany(mappedBy = "myCountries",cascade = CascadeType.ALL)
//    private List<UserApp> userAppsCountries;


    public UserApp getUserCountries() {
        return userCountries;
    }

    public void setUserCountries(UserApp userCountries) {
        this.userCountries = userCountries;
    }

    @ManyToOne
    UserApp userCountries;

//    public List<UserApp> getUserAppsCountries() {
//        return userAppsCountries;
//    }
//
//    public void setUserAppsCountries(List<UserApp> userAppsCountries) {
//        this.userAppsCountries = userAppsCountries;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MyCountries{" +
                "id=" + country_id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }




    //    @ManyToMany(mappedBy = "favoriteRecipeModels",cascade= CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<UserApp> userFavRecipe;
}
