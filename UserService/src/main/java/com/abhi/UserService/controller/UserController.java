package com.abhi.UserService.controller;

import com.abhi.UserService.entity.User;
import com.abhi.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll()
    {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable int id)
    {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception {
        int id = user.getId();
        System.out.println(id);
      return userService.saveUser(user);
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteByUserId(@PathVariable int id)
    {
        return userService.deleteByUserId(id);
    }


}
