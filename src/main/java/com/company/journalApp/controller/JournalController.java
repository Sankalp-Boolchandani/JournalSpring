package com.company.journalApp.controller;

import com.company.journalApp.entity.JournalEntry;
import com.company.journalApp.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @PostMapping
    public void createEntry(@RequestBody JournalEntry journalEntry){
        journalService.saveEntry(journalEntry);
    }

    @GetMapping
    public List<JournalEntry> getALl(){
        return journalService.getAll();
    }

}
