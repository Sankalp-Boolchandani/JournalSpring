package com.company.journalApp.controller;

import com.company.journalApp.entity.User;
import com.company.journalApp.service.UserService;
import com.company.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

//    @GetMapping
//    public List<User> getAllUser(){
//        return userService.getAllUsers();
//    }

    @GetMapping
    public User getUserByUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        return userService.getUserByUsername(username);
    }


    @PutMapping
    public void updateUserDetails(@RequestBody User user){
        // check point 1 at the bottom of the class
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        userService.updateUserDetails(username, user);
    }

    @DeleteMapping
    public void deleteUser(){
        // check point 1 at the bottom of the class
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUser(authentication.getName());
    }

    @GetMapping("/greet/{city}")
    public ResponseEntity<String> greetUser(@PathVariable String city){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String res=weatherService.getWeather(city, authentication.getName());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}


//-------------------------------------------------------------------------------------------------------------------------------
// 1.
// removing username from path variable as it is not an acceptable practise when trying to get/delete/update user details.
// Instead, we get the details as username and password using Spring's SecurityContextHolder.
// the details(username and password) are automatically stored in SecurityContextHolder when a user is authenticated
// with the help of code we wrote in SecurityConfig class
//-------------------------------------------------------------------------------------------------------------------------------