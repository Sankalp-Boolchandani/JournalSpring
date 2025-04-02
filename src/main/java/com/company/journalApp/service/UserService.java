package com.company.journalApp.service;

import com.company.journalApp.entity.User;
import com.company.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        userRepository.save(user);
    }

    // we are not using the earlier described save method as that method contains hashing the password
    // and while adding the journal, it is hashing an already hashed password which is creating an issue,
    // thats why this new method without changing password or other details.
    public void saveUserOnJournalCreation(User user){
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

    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }
}
