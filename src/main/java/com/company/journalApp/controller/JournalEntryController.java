package com.company.journalApp.controller;

import com.company.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/journal")                // common for the whole rest controller
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
    public JournalEntry getJournalById(@PathVariable Long id){              // path variable is what the ide looks for in the incoming url inside the curly brackets
        return journalEntryList.get(id);
    }

    @DeleteMapping("/id/{id}")
    public JournalEntry deleteJournalById(@PathVariable Long id){
        return journalEntryList.remove(id);
    }

    @PutMapping("/id/{id}")
    public String updateJournal(@PathVariable Long id, @RequestBody JournalEntry journalEntry){             // inside put mapping, you give path variable that is to look the id in the path and the request body with the data that is to be updated from the incoming request
        journalEntryList.put(id, journalEntry);
        return "updated!!!!";
    }

}
