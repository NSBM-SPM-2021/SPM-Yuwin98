package com.spmapp.api.repository;

import com.spmapp.api.models.Response;
import com.spmapp.api.models.User;
import com.spmapp.api.models.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class UserRepository {

    public static final Logger log = LoggerFactory.getLogger(UserRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUserName(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setUserId(rs.getString("user_id"));

        return user;
    };

    RowMapper<UserResponse> userResponseRowMapper = (rs, rowNum) -> {
        UserResponse user = new UserResponse();
        user.setUserName(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setUserId(rs.getString("user_id"));

        return user;
    };


    public UserRepository() {
    }

    public List<UserResponse> findAll() {
        String sql = "Select user_id, username, email from user ORDER BY id DESC";
        return jdbcTemplate.query(sql, userResponseRowMapper);
    }

    public UserResponse findOne(String id) {
        String sql = "Select user_id, username, email from user where user_id = ?  ";
        return jdbcTemplate.queryForObject(sql, userResponseRowMapper, id);
    }

    public Response<User> addUser(User user) {
        Response<User> response = new Response<>();
        log.info(user.getUserId() + ":" + user.getUserName() + ":" + user.getEmail());
        String sql = "Insert into user(user_id, username, password, email) VALUES(?,?,?,?)";

        int insert = jdbcTemplate.update(sql, user.getUserId(), user.getUserName(), user.getPassword(), user.getEmail());

        if (insert == 1) {
            response.setStatusCode(201);
            response.setMessage("User created");
            return response;
        }

        response.setStatusCode(500);
        response.setMessage("User creation failed");

        return response;
    }

    public Response<User> deleteUser(String id) {
        Response<User> response = new Response<>();
        String sql = "Delete from user where user_id = ?";
        int delete = jdbcTemplate.update(sql, id);

        if(delete == 1) {
            response.setStatusCode(200);
            response.setMessage("User deleted successfully");
            return response;
        }

        return response;
    }

}
