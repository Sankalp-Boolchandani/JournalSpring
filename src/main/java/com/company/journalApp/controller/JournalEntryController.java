package com.company.journalApp.controller;

import com.company.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/journal")
public class JournalEntryController {

    Map<Long, JournalEntry> journalEntryList=new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAllEntries(){
        return new ArrayList<>(journalEntryList.values());
    }

    @PostMapping
    public String addJournalEntry(@RequestBody JournalEntry journalEntry){
        journalEntryList.put(journalEntry.getId(), journalEntry);
        return "added!";
    }

    @GetMapping("/id/{id}")
    public JournalEntry getJournalById(@PathVariable Long id){
        return journalEntryList.get(id);
    }

    @DeleteMapping("/id/{id}")
    public JournalEntry deleteJournalById(@PathVariable Long id){
        return journalEntryList.remove(id);
    }

}
