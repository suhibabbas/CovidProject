package com.example.CovidProject.Repositories;

import com.example.CovidProject.Models.MyCountries;
import com.example.CovidProject.Models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyCountriesRepository extends JpaRepository<MyCountries,Long> {
    MyCountries findByName(String name);

}
