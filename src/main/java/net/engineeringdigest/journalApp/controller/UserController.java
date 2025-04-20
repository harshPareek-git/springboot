package net.engineeringdigest.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@Slf4j
//@RequestMapping("journal")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            userService.createEntry(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Exception ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllEntries();
        if (allUsers != null && !allUsers.isEmpty()) {
            return new ResponseEntity<>(allUsers,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @SuppressWarnings("OptionalIsPresent")
    @GetMapping("id/{myId}")
    public ResponseEntity<User> getUserById(@PathVariable ObjectId myId) {
        Optional<User> userId = userService.findEntryById(myId);
        if (userId.isPresent()) {
            return new ResponseEntity<>(userId.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("id/{myId}")
    public ResponseEntity<User> deleteUserById(@PathVariable ObjectId myId) {
          userService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

        @PutMapping("/updateUser/{userName}")
    public ResponseEntity<User> updateUserById(@RequestBody User user,@PathVariable String userName) {
        User userInDB = userService.findByUserName(userName);
        if (userInDB!=null){
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userService.createEntry(userInDB);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


