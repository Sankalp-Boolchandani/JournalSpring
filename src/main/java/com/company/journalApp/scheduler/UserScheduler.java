package com.company.journalApp.scheduler;

import com.company.journalApp.constants.AppConstants;
import com.company.journalApp.entity.User;
import com.company.journalApp.enums.Sentiment;
import com.company.journalApp.repository.impl.UserRepositoryImpl;
import com.company.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 9 ? * SUN")
    public void fetchUsersAndSendEmail(){
        List<User> users = userRepository.getEmailListUsers();
        for (User user: users){

            // what below line actually does is get all the journal entries that were created 7 days earlier fetches the
            // sentiment out of them. Only the content, not journal name.
            // This was changed, earlier it gave journal content
            List<Sentiment> sentiments = user.getJournalEntries()
                    .stream().filter(
                    x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(x -> x.getSentiment())
                    .toList();

            // this is a map created which consists of Sentiment and their count in the last 7 days journal entries
            Map<Sentiment, Integer> sentimentCount=new HashMap<>();
            for (Sentiment sentiment: sentiments){
                if (sentiment!=null){
                    sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment, 0)+1);
                }
            }

            // this is done to check from the count we get from above map to check which was the most frequent user sentiment
            Sentiment mostFrequentSentiment=null;
            int maxCount=0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCount.entrySet()){
                if (entry.getValue() > maxCount){
                    maxCount=entry.getValue();
                    mostFrequentSentiment=entry.getKey();
                }
            }

            // sending email if the sentiment was not null
            if (mostFrequentSentiment!=null){
                emailService.sendMail(user.getEmail(), AppConstants.SENTIMENT_ANALYSIS_EMAIL_SUBJECT_LINE, mostFrequentSentiment.toString());
            }
        }
    }

}
