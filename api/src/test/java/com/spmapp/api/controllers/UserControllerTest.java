package com.spmapp.api.controllers;

import com.spmapp.api.ApiApplication;
import com.spmapp.api.models.Product;
import com.spmapp.api.models.Response;
import com.spmapp.api.models.User;
import com.spmapp.api.models.UserResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = ApiApplication.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    @Order(1)
    void addUser() {
        User user = new User();
        user.setUserId("123");
        user.setUserName("Test User");
        user.setEmail("Test Email");
        user.setPassword("Test Password");

        Response<User> response = userController.addUser(user);

        assertEquals("User created", response.getMessage());
        assertEquals(201, (long)response.getStatusCode());
        assertEquals("123", response.getData().get().getUserId());
    }

    @Test
    @Order(2)
    void getUser() {
        UserResponse user = userController.getUser("123");
        assertEquals("Test Email", user.getEmail());
        assertEquals("Test User", user.getUserName());
        assertEquals("123", user.getUserId());
    }

    @Test
    @Order(3)
    void updateUser() {
        User user = new User();
        user.setUserId("123");
        user.setUserName("Test User");
        user.setEmail("Test Email");
        user.setPassword("Test Password");

        user.setUserName("Test Username Updated");

        Response<User> response = userController.updateUser(user);

        assertEquals("User updated", response.getMessage());
        assertEquals(200, (long)response.getStatusCode());
        assertEquals("Test Username Updated", response.getData().get().getUserName());

    }

    @Test
    @Order(4)
    void deleteUser() {
        Response<User> response = userController.deleteUser("123");

        assertEquals("User deleted successfully", response.getMessage());
        assertEquals(200, (long)response.getStatusCode());
    }
}