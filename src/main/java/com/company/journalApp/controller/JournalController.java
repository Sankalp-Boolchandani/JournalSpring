package com.company.journalApp.controller;

import com.company.journalApp.entity.JournalEntry;
import com.company.journalApp.entity.User;
import com.company.journalApp.service.JournalService;
import com.company.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @PostMapping("{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry, @PathVariable String username){
        try {
            journalService.saveEntry(journalEntry, username);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{username}")
    public ResponseEntity<?> getAllEntriesOfUser(@PathVariable String username){
        User user = userService.getUserByUsername(username);
        List<JournalEntry> list=user.getJournalEntries();
        if (list!=null){
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId id){
        Optional<JournalEntry> journalEntry=journalService.getById(id);
        if (journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<String> deleteJournal(@PathVariable ObjectId id){
        journalService.deleteJournal(id);
        return new ResponseEntity<>("Deleted!!", HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntry> updateJournal(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry){
        JournalEntry resposeEntry=journalService.updateJournalDetails(id, journalEntry);
        if (resposeEntry!=null){
            return new ResponseEntity<>(resposeEntry, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
