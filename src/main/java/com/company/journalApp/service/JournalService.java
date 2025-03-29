package com.company.journalApp.service;

import com.company.journalApp.entity.JournalEntry;
import com.company.journalApp.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
