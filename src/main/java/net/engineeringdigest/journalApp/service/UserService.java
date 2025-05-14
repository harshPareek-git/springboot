package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class UserService  {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

//    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
    public void saveUser(User user){
        userRepository.save(user);
    }

    public void saveNewUser(User user){
        try {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        }catch (Exception e){
            logger.error("Exception occurred for {}",user.getUserName(),e);
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllEntries(){
        return userRepository.findAll();
    }
    public Optional<User> findEntryById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        userRepository.deleteById(id);
    }


    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

}
