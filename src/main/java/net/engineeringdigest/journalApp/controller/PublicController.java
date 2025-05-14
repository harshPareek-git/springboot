package net.engineeringdigest.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @GetMapping("health-check")
    public String health(){
        System.out.println("------");
        return "OK";
    }


    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            userService.saveNewUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Exception ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }




}
