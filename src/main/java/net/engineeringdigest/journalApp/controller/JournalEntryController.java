package net.engineeringdigest.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequestMapping("/journal")
@Slf4j
//@RequestMapping("journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UserService userService;
    @PostMapping("/createEntry/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName) {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.createEntry(myEntry,userName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Exception ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getAllEntries")
    public ResponseEntity<JournalEntry> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JournalEntry> allEntries = user.getJournalEntries();
        if (allEntries != null && !allEntries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @SuppressWarnings("OptionalIsPresent")
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findEntryById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
        JournalEntry oldEntry = journalEntryService.findEntryById(myId).orElse(null);
        if (oldEntry != null) {
//            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
//            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
//            journalEntryService.createEntry(oldEntry);
//            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}


