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

    @GetMapping("/users")
    List<UserResponse> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    UserResponse getUser(@PathVariable String id) {
        return userRepository.findOne(id);
    }

    @PostMapping("/users")
    Response<User> addUser(@RequestBody User user) {
        return userRepository.addUser(user);
    }

    @PutMapping("/users")
    Response<User> updateUser(@RequestBody User user) {return userRepository.updateUser(user);}

    @DeleteMapping("/users/{id}")
    Response<User> deleteUser(@PathVariable String id) {
        return userRepository.deleteUser(id);
    }



}
