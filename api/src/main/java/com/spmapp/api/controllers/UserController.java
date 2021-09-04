package com.spmapp.api.controllers;

import com.spmapp.api.models.Response;
import com.spmapp.api.models.User;
import com.spmapp.api.models.UserResponse;
import com.spmapp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;
    private final String origin = "http://spm-bookshop.s3-website-us-east-1.amazonaws.com";

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CrossOrigin(origins = origin)
    @GetMapping("/users")
    List<UserResponse> getAllUsers() {
        return userRepository.findAll();
    }

    @CrossOrigin(origins = origin)
    @GetMapping("/users/{id}")
    UserResponse getUser(@PathVariable String id) {
        return userRepository.findOne(id);
    }

    @CrossOrigin(origins = origin)
    @PostMapping("/users")
    Response<User> addUser(@RequestBody User user) {
        return userRepository.addUser(user);
    }

    @CrossOrigin(origins = origin)
    @PutMapping("/users")
    Response<User> updateUser(@RequestBody User user) {return userRepository.updateUser(user);}

    @CrossOrigin(origins = origin)
    @DeleteMapping("/users/{id}")
    Response<User> deleteUser(@PathVariable String id) {
        return userRepository.deleteUser(id);
    }



}
