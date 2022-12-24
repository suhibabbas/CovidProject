package com.example.CovidProject.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
public class UserApp  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public UserApp() {

    }


//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "userFavRecipes",
//            joinColumns = @JoinColumn(name = "Recipe_id"),
//            inverseJoinColumns = @JoinColumn(name = "User_id")
//    )
//    List<RecipeModel> favoriteRecipeModels;


//    public List<MyCountries> getMyCountries() {
//        return myCountries;
//    }
//
//    public void setMyCountries(List<MyCountries> myCountries) {
//        this.myCountries = myCountries;
//    }

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "userAppsCountries",
//            joinColumns = @JoinColumn(name="country_id"),
//            inverseJoinColumns = @JoinColumn(name = "User_id")
//    )
//    List<MyCountries> myCountries;


    public List<MyCountries> getCountries() {
        return countries;
    }

    public void setCountries(List<MyCountries> countries) {
        this.countries = countries;
    }

    @OneToMany(mappedBy = "userCountries")///////////////////////////
    List<MyCountries> countries;

    public void setUsername(String username) {
        this.username = username;
    }

    public UserApp(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }




    @Override
    public String toString() {
        return "UserApp{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
