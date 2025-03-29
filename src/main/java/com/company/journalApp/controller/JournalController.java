package com.company.journalApp.controller;

import com.company.journalApp.entity.JournalEntry;
import com.company.journalApp.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalService.saveEntry(journalEntry);
        return journalEntry;
    }

    @GetMapping
    public List<JournalEntry> getALl(){
        return journalService.getAll();
    }

    @GetMapping("id/{id}")
    public JournalEntry getJournalById(@PathVariable ObjectId id){
        return journalService.getById(id).orElse(null);
    }

    @DeleteMapping("id/{id}")
    public String deleteJournal(@PathVariable ObjectId id){
        return journalService.deleteJournal(id);
    }

    @PutMapping("id/{id}")
    public boolean updateJournal(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry){
        return journalService.updateJournalDetails(id, journalEntry);
    }

}
