package com.company.journalApp.repository;

import com.company.journalApp.repository.impl.UserRepositoryImpl;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    void getEmailListUsers(){
        assertNotNull(userRepository.getEmailListUsers());
    }

}
