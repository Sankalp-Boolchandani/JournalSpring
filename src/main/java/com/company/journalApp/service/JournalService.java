package com.company.journalApp.service;

import com.company.journalApp.entity.JournalEntry;
import com.company.journalApp.entity.User;
import com.company.journalApp.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService userService;

    // line 34 is a perfect example of transaction annotation working.
    // when uncommented, it sets the username as null and since it requires a not null data, it would refuse creation/updation
    // causing the user to not being saved. And since the journal is already saved, it too would roll back!!!
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        try {
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry savedJournal = journalRepository.save(journalEntry);
            User user = userService.getUserByUsername(username);
            user.getJournalEntries().add(savedJournal);
            // user.setUsername(null);
            // userService.saveUser(user);
            userService.saveUserOnJournalOperation(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<JournalEntry> getAll() {
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> getById(String username, ObjectId id) {
        User user = userService.getUserByUsername(username);
        return user.getJournalEntries().stream().filter(x->x.getId().equals(id)).findFirst();
    }

    @Transactional
    public boolean deleteJournal(ObjectId id, String username) {
        boolean removed = false;
        try {
            User user = userService.getUserByUsername(username);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed){
                userService.saveUserOnJournalOperation(user);
                journalRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return removed;
    }

    public boolean updateJournalDetails(ObjectId id, JournalEntry journalEntryNew, String username) {
        User user = userService.getUserByUsername(username);
        if (user!=null){
            Optional<JournalEntry> journalEntryOld = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).findAny();
            if (journalEntryOld.isPresent()){
                JournalEntry journalEntry = journalEntryOld.get();
                if (journalEntryNew.getName()!=null || !journalEntryNew.getName().isEmpty()){
                    journalEntry.setName(journalEntryNew.getName());
                }
                if (journalEntryNew.getContent()!=null || !journalEntryNew.getContent().isEmpty()){
                    journalEntry.setContent(journalEntryNew.getContent());
                }
                journalRepository.save(journalEntry);
                return true;
            }
        }
        return false;
    }
}
