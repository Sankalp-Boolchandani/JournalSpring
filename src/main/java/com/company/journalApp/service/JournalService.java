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

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        try {
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry savedJournal = journalRepository.save(journalEntry);
            User user = userService.getUserByUsername(username);
            user.getJournalEntries().add(savedJournal);
//            user.setUsername(null);
//            line 31 is a perfect example of transaction annotation working.
//            when uncommented, it sets the username as null and since it requires a not null data, it would refuse creation/updation
//            causing the user to not being saved. And since the journal is already saved, it too would roll back!!!
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<JournalEntry> getAll() {
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalRepository.findById(id);
    }

    public void deleteJournal(ObjectId id, String username) {
        User user = userService.getUserByUsername(username);
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.saveUser(user);
        journalRepository.deleteById(id);
    }

    public JournalEntry updateJournalDetails(ObjectId id, JournalEntry journalEntryNew) {
        JournalEntry journalEntryOld=journalRepository.findById(id).orElse(null);
        if (journalEntryNew.getName()!=null || !journalEntryNew.getName().isEmpty()){
            journalEntryOld.setName(journalEntryNew.getName());
        }
        if (journalEntryNew.getContent()!=null || !journalEntryNew.getContent().isEmpty()){
            journalEntryOld.setContent(journalEntryNew.getContent());
        }
        journalRepository.save(journalEntryOld);
        return journalEntryOld;
    }
}
