package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @PostMapping("/createEntry")
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        journalEntryService.createEntry(myEntry);
        return myEntry;
    }
    @GetMapping("/getAllEntries")
    public List<JournalEntry> getAllEntrires(){
       return journalEntryService.getAllEntries();
    }

    @GetMapping("id/{myId}")
    public Optional<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        return Optional.ofNullable(journalEntryService.findEntryById(myId).orElse(null));
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myId){
         journalEntryService.deleteById(myId);
         return true;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.findEntryById(id).orElse(null);
            if (oldEntry !=null){
                oldEntry.setTitle(newEntry.getTitle() != null && newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(newEntry.getContent() !=null && newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.createEntry(oldEntry);
            }
            return oldEntry;
    }
}


