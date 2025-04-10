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

    private Map<String, JournalEntry> journalEntryMap = new HashMap<>();

//    @GetMapping()
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntryMap.values());
//    }

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
        return journalEntryService.findEntryById(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable ObjectId myId){
        return journalEntryMap.remove(myId);
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry){
        return journalEntryMap.put(null,myEntry);
    }
}


