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

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/users")
    List<UserResponse> getAllUsers() {
        return userRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/users/{id}")
    UserResponse getUser(@PathVariable String id) {
        return userRepository.findOne(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/users")
    Response<User> addUser(@RequestBody User user) {
        return userRepository.addUser(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/users")
    Response<User> updateUser(@RequestBody User user) {return userRepository.updateUser(user);}

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/users/{id}")
    Response<User> deleteUser(@PathVariable String id) {
        return userRepository.deleteUser(id);
    }



}
