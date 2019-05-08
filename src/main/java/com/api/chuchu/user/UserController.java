package com.api.chuchu.user;

import com.api.chuchu.user.User;
import com.api.chuchu.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @CrossOrigin
    @PostMapping("/aUsers")
    public void createUsers(@RequestBody List<User> users) {
        users.forEach(user -> userRepository.save(user));
    }

}
