package com.spmapp.api.controllers;

import com.spmapp.api.models.User;
import com.spmapp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    User getUser(@PathVariable String id) {
        return userRepository.findOne(id);
    }

    @PostMapping("/users")
    User addUser(@RequestBody User user) {
        return userRepository.addUser(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/users/{id}")
    User deleteUser(@PathVariable String id) {
        return userRepository.deleteUser(id);
    }

}
