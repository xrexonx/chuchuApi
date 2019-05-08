package com.api.chuchu.user;

import com.api.chuchu.exception.ResourceNotFoundException;
import com.api.chuchu.user.User;
import com.api.chuchu.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @CrossOrigin
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(
                    () -> new ResourceNotFoundException("User", "id", userId)
                );
    }

    @CrossOrigin
    @PutMapping("/users/{id}")
    public User updateUser(
            @PathVariable(value = "id") Long userId,
            @Valid @RequestBody User userDetails
    ) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setWebsite(userDetails.getWebsite());

        return userRepository.save(user);
    }

    @CrossOrigin
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }

}
