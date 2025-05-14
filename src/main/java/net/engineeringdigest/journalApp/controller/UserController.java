package net.engineeringdigest.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;



    @Autowired
    WeatherService weatherService;

//    @GetMapping("/getAllUsers")
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> allUsers = userService.getAllEntries();
//        if (allUsers != null && !allUsers.isEmpty()) {
//            return new ResponseEntity<>(allUsers,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
@PostMapping("/register")
public ResponseEntity<User> registerUser(@RequestBody User user) {
    try {
        // Check if user already exists
        if (userService.findByUserName(user.getUserName()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.saveNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
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
    public ResponseEntity<User> deleteUserById(@PathVariable ObjectId myId,@PathVariable String userName) {
          userService.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDB = userService.findByUserName(userName);
        if (userInDB != null) {
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userService.saveNewUser(userInDB);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<User> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if (weatherResponse != null) {
            greeting = " the weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }
}