package com.company.journalApp.scheduler;

import com.company.journalApp.constants.AppConstants;
import com.company.journalApp.entity.User;
import com.company.journalApp.repository.impl.UserRepositoryImpl;
import com.company.journalApp.service.EmailService;
import com.company.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Scheduled(cron = "0 0 9 ? * SUN")
    public void fetchUsersAndSendEmail(){
        List<User> users = userRepository.getEmailListUsers();
        for (User user: users){

            // what below line actually does is get all the journal entries that were created 7 days earlier fetches the
            // content out of them. Only the content, not journal name
            List<String> lastSevenDaysContent = user.getJournalEntries().stream().filter(
                    x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(x -> x.getContent())
                    .toList();
            String sentimentAnalysisContent = String.join(" ", lastSevenDaysContent);
            String sentiment = sentimentAnalysisService.getSentiment(sentimentAnalysisContent);
            emailService.sendMail(user.getEmail(), AppConstants.SENTIMENT_ANALYSIS_EMAIL_SUBJECT_LINE, sentiment);
        }
    }

}
