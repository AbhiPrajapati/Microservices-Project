package com.abhi.UserService.service;

import com.abhi.UserService.entity.User;
import com.abhi.UserService.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<User>> findAll()
    {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<User> findById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public ResponseEntity<User>  saveUser(User user)  {
        if (user.getId() > 0) {
            throw new IllegalArgumentException("Cannot create a user with an existing ID. ID must be null.");
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    public ResponseEntity<User> updateUser(User user)
    {
        return ResponseEntity.ok(userRepository.save(user));
    }

    public ResponseEntity<User> deleteByUserId(int id)
    {
         Optional<User> optionalUser = userRepository.findById(id);
         try {
             userRepository.deleteById(id);
             return optionalUser.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
         }catch (Exception e)
         {
             System.out.println(e);
             return ResponseEntity.badRequest().build();
         }
    }

}
