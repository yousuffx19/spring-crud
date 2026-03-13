package com.example.crud.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.exceptions.ResourceNotFoundException;
import com.example.crud.models.User;
import com.example.crud.repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id){
        User userData = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userData.setEmail(user.getEmail());
        userData.setUserName(user.getUserName());
        return userRepository.save(userData);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        User userData = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(userData);    
    }
}
