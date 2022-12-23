package com.example.CovidProject.Controller;

import com.example.CovidProject.JsonData.Countries;
import com.example.CovidProject.JsonData.Country;
import com.example.CovidProject.JsonData.Root;
import com.example.CovidProject.JsonData.Totals;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class MainController {

    String from = "";
    String to = "";
    String country = "";

    @GetMapping("/")
    public String getHomePage(Model model) throws IOException {

        if(from.length()!=0){
            Country[] countries = ReadFromCountryAPI();
            model.addAttribute("covidList", countries);
        }

        Totals totals = ReadFromAPI();

//        for (Result r: results
//             ) {
//            System.out.println(r.date);
//        }
        System.out.println(totals.totalConfirmed);
        model.addAttribute("totals", totals);


        return "index";
    }

    @GetMapping("/allCountries")
    public String getAllCountries(Model model) throws IOException {

        Root root = allCountriesAPI();

        for (Countries c: root.countries
             ) {
            System.out.println(c.country);
        }
        model.addAttribute("allCountriesList",root.countries);

        return "allCountries";
    }


    @PostMapping("/search")
    public RedirectView search(@RequestParam String fromDate, @RequestParam String toDate, @RequestParam String countryName){
        System.out.println(fromDate);
        from = fromDate;
        to = toDate;
        country = countryName;
        return new RedirectView("/");
    }

    public Country[] ReadFromCountryAPI() throws IOException {
        String dataJson = "" ;

        try {
            //Make connection
            URL covidURL = new URL("https://api.covid19api.com/country/"+country+"/status/confirmed?from="+from+"T00:00:00Z&to="+to+"T00:00:00Z");
            HttpURLConnection connection = (HttpURLConnection) covidURL.openConnection();
            //Send request
            connection.setRequestMethod("GET");
            connection.connect();
            //Read the response
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            BufferedReader urlBuffered = new BufferedReader(reader);
            //Get the data
            dataJson = urlBuffered.readLine();
//            System.out.println(dataJson);
            WriteOnJsonFile(dataJson);
        }catch (Exception e){
        }

        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson gson = gsonBuilder.create();

        Country[] countries =gson.fromJson(dataJson, Country[].class);

        return countries;
    }

    public Totals ReadFromAPI() throws IOException {
        String dataJson = "" ;

        try {
            //Make connection
            URL covidURL = new URL("https://api.covid19api.com/world/total");
            HttpURLConnection connection = (HttpURLConnection) covidURL.openConnection();
            //Send request
            connection.setRequestMethod("GET");
            connection.connect();
            //Read the response
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            BufferedReader urlBuffered = new BufferedReader(reader);
            //Get the data
            dataJson = urlBuffered.readLine();
//            System.out.println(dataJson);
            WriteOnJsonFile(dataJson);
        }catch (Exception e){
        }

        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson gson = gsonBuilder.create();

        Totals results =gson.fromJson(dataJson, Totals.class);

        return results;
    }

    public Root allCountriesAPI() throws IOException {
        String dataJson = "" ;

        try {
            //Make connection
            URL covidURL = new URL("https://api.covid19api.com/summary");
            HttpURLConnection connection = (HttpURLConnection) covidURL.openConnection();
            //Send request
            connection.setRequestMethod("GET");
            connection.connect();
            //Read the response
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            BufferedReader urlBuffered = new BufferedReader(reader);
            //Get the data
            dataJson = urlBuffered.readLine();
            System.out.println(dataJson);
            WriteOnJsonFile(dataJson);
        }catch (Exception e){
        }

        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson gson = gsonBuilder.create();

        Root results =gson.fromJson(dataJson,Root.class);

        return results;
    }


    public static void WriteOnJsonFile(String dataJson) throws IOException {
        Gson gson = new Gson();

        Country[] countries =gson.fromJson(dataJson, Country[].class);

//        for (Result r:results
//             ) {
//            System.out.println(r.country);
//        }

        File file = new File("test.json");
        try(FileWriter fileWriter = new FileWriter(file)) {
            gson.toJson(countries,fileWriter);
        }
    }
}
