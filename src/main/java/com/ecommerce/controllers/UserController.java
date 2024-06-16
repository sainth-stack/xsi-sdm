package com.ecommerce.controllers;

import com.ecommerce.models.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServices userServices;

    @PostMapping("create-user")
    public String createUser(@RequestBody User user) {
        User isExist = userRepository.findByEmail(user.getEmail());
        if (isExist != null) {
            return "User Already Exist";
        } else {
            User savedUser = userRepository.save(user);
            return "User Created Successfully" + savedUser.getId();
        }
    }

    @DeleteMapping("user/{id}")
    public String deleteUser(@PathVariable Long id) {
       userRepository.deleteById(id);
       return "User Deleted Successfully";
    };

    @GetMapping("users")
    public List<User> getAllUsers() {
       List<User> users = userRepository.findAll();
        return users;
    };

    @GetMapping("/get-profile")
    public User findUserByJWT(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userServices.findUserByJwt(jwt);
        return user;
    }
}
