package com.company.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    // demo test method for testing out email sending
    public void sendMail(){
        try {
            SimpleMailMessage msg=new SimpleMailMessage();
            msg.setTo("boolchandanisankalp@gmail.com");
            msg.setSubject("Mail test 2");
            msg.setText("it worked the third time, yml had indentation error nothing big! xd");
            javaMailSender.send(msg);
        } catch (Exception e) {
            log.error("Exception while sending email ", e);
            throw new RuntimeException(e);
        }
    }


    // method overloading - main method to send email from outside the class
    public void sendMail(String to, String subject, String message){
        try {
            SimpleMailMessage msg=new SimpleMailMessage();
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(message);
            javaMailSender.send(msg);
        } catch (Exception e) {
            log.error("Exception while sending email ", e);
            throw new RuntimeException(e);
        }
    }

}
