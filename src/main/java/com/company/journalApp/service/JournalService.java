package com.company.journalApp.service;

import com.company.journalApp.entity.JournalEntry;
import com.company.journalApp.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public void saveEntry(JournalEntry journalEntry) {
        journalRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalRepository.findById(id);
    }

    public void deleteJournal(ObjectId id) {
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
