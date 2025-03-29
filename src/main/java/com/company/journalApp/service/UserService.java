package com.company.journalApp.service;

import com.company.journalApp.entity.User;
import com.company.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void updateUserDetails(String username, User user){
        User existingUser=getUserByUsername(username);
        if (existingUser!=null){
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            saveUser(existingUser);
        }
    }

}
