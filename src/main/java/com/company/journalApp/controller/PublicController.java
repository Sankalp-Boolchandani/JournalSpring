package com.company.journalApp.controller;

import com.company.journalApp.entity.User;
import com.company.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    // moving createUser to public controller since we don't want authentication while trying to add a user
    // but for other user related functions, authentication is required.
    // Adding "/user" endpoint to authenticated after this
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }


}
