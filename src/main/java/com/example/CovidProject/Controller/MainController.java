package com.example.CovidProject.Controller;

import com.example.CovidProject.JsonData.Countries;
import com.example.CovidProject.JsonData.Country;
import com.example.CovidProject.JsonData.Root;
import com.example.CovidProject.JsonData.Totals;
import com.example.CovidProject.Models.MyCountries;
import com.example.CovidProject.Models.UserApp;
import com.example.CovidProject.Repositories.MyCountriesRepository;
import com.example.CovidProject.Repositories.UserAppRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Controller
public class MainController {

    String from = "";
    String to = "";
    String country = "";

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserAppRepository userAppRepository;
    private final MyCountriesRepository myCountriesRepository;

    public MainController(UserAppRepository userAppRepository, MyCountriesRepository myCountriesRepository) {
        this.userAppRepository = userAppRepository;
        this.myCountriesRepository = myCountriesRepository;
    }


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

        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        UserApp userApp = userAppRepository.findUserAppsByUsername(currentUser);

        List<MyCountries> allUserCountries = myCountriesRepository.findAll();



        Root root = allCountriesAPI();

        if(root==null){
            root = allCountriesFile("test.json");
        }

//        for (Countries c: root.countries
//             ) {
//            System.out.println(c.country);
//        }
        model.addAttribute("allCountriesList",root.countries);

        return "allCountries";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signupNewUser(@RequestParam String username,@RequestParam String password){
        UserApp userApp = userAppRepository.findUserAppsByUsername(username) ;
        if(userApp != null){
            return "signup";
        }
        UserApp userApp1 = new UserApp(username,passwordEncoder.encode(password));
        userAppRepository.save(userApp1);

        return "login";
    }

    @PostMapping("/add")
    public RedirectView add(@RequestParam String country,@RequestParam String date){
        System.out.println(country + date);

        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        UserApp userApp = userAppRepository.findUserAppsByUsername(currentUser);

        MyCountries myCountries = new MyCountries(country,date);

        System.out.println(myCountries.getDate()+"////////////////////////////////////////////////////////////////");
        userApp.getCountries().add(myCountries);
        myCountries.setUserCountries(userApp);
        myCountriesRepository.save(myCountries);


        System.out.println(myCountries.getUserCountries().getUsername()+"00000000000000000000000000000000000000000000000000000000000000000000000000000000000");

            return new RedirectView("allCountries");
    }

    @PostMapping("/search")
    public RedirectView search(@RequestParam String fromDate, @RequestParam String toDate, @RequestParam String countryName){
        System.out.println(fromDate);
        from = fromDate;
        to = toDate;
        country = countryName;
        return new RedirectView("/");
    }


    @GetMapping("/myRecords")
    public String userRecords(Model model){
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        UserApp userApp = userAppRepository.findUserAppsByUsername(currentUser);

        model.addAttribute("allRecords",userApp.getCountries());

        return "myRecords";
    }

    @PostMapping("/deleteRecord")
    public RedirectView deleteRecord(@RequestParam String name ){

        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        UserApp userApp = userAppRepository.findUserAppsByUsername(currentUser);

        for (MyCountries m: userApp.getCountries()
             ) {
            if(m.getName().equals(name)){
                myCountriesRepository.deleteById(m.getCountry_id());
            }
        }

        return new RedirectView("/myRecords");
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
//            WriteOnJsonFile(dataJson);
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
//            WriteOnJsonFile(dataJson);
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
//            WriteOnJsonFile(dataJson);
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

    public static Root allCountriesFile(String fileName){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson gson = gsonBuilder.create();
        Root root = gson.fromJson(fileReader,Root.class);

        return  root;
    }
}
