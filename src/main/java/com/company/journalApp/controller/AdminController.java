package com.company.journalApp.controller;

import com.company.journalApp.entity.User;
import com.company.journalApp.service.AppConfigDataService;
import com.company.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppConfigDataService appConfigDataService;

    // removing get all users from user controller because no user without admin privileges should be allowed to view other users data.
    @GetMapping("/get-all-users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("create-admin")
    public void createAdmin(@RequestBody User user){
        userService.createAdmin(user);
    }

    @GetMapping("/refresh-app-cache")
    public void refreshAppCache(){
        appConfigDataService.init();
//        this controller is written for the purpose of refreshing the app cache. say suppose you are running the application
//        and after the application is in running status, there are some changes made in DB config data. So instead of restarting
//        the server, this would invoke init method of service class which does initializes the appConfig meaning emptying the
//        appCache array and then calling the DB again for the cache data.
    }

}
