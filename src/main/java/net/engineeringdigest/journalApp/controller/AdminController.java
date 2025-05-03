package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> allEntries = userService.getAllEntries();
        if (allEntries != null && !allEntries.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
     @PostMapping("/create-admin-user")
       public void createAdminUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }

}
