package com.company.journalApp.controller;

import com.company.journalApp.entity.User;
import com.company.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @GetMapping("{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }


    @PutMapping("{username}")
    public void updateUserDetails(@PathVariable String username, @RequestBody User user){
        userService.updateUserDetails(username, user);
    }

}
