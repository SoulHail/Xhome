package com.codebear.xhome.controller;

import com.codebear.xhome.entity.User;
import com.codebear.xhome.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class JpaUserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        Optional<User> user = userMapper.findById(id);
        return user.get();
    }

    @GetMapping("/user")
    public User insertUser(User user) {
        User save = userMapper.save(user);
        return save;
    }
}
