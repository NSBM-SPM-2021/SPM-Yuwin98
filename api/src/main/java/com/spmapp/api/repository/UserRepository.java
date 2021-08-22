package com.spmapp.api.repository;

import com.github.javafaker.Faker;
import com.spmapp.api.models.User;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRepository {

    Faker faker = new Faker();

    private  List<User> userList = new ArrayList();

    public UserRepository() {
        for(int i = 0; i < 100; i++) {
            String id = faker.idNumber().ssnValid();
            String userName = faker.name().username();
            String password = faker.internet().password(6,18);
            String email = faker.internet().emailAddress();
            User user = new User(id,userName, password, email);
            userList.add(user);
        }
    }

    public List<User> findAll() {
        return userList;
    }

    public User findOne(String id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst().get();
    }

    public User addUser(User user) {
        userList.add(user);
        return user;
    }

    public  User deleteUser(String id) {
        User userToDelete = userList.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(new User("-1","N/A","N/A", ""));

        userList.remove(userToDelete);
        return userToDelete;
    }
}
