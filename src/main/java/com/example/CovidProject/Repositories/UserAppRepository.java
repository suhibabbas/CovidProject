package com.example.CovidProject.Repositories;

import com.example.CovidProject.Models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp,Long> {
    UserApp findUserAppsByUsername(String username);
    UserApp findUserAppById(Long id);
}
