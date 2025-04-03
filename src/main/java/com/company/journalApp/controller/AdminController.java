package com.company.journalApp.controller;

import com.company.journalApp.entity.User;
import com.company.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    // removing get all users from user controller because no user without admin privileges should be allowed to view other users data.
    @GetMapping("/get-all-users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("create-admin")
    public void createAdmin(@RequestBody User user){
        userService.createAdmin(user);
    }

}
