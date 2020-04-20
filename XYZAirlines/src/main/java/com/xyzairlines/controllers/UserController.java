package com.xyzairlines.controllers;

import com.xyzairlines.models.User;
import com.xyzairlines.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Add new admin users
    @PostMapping("/save")
    public void saveUser(@RequestBody User user) {
        this.userRepository.save(user);
    }

    //Check username and password before giving access
    @PostMapping("/checklogon")
    public String validateLogOn(@RequestBody User user) {
        String result;
        Optional<User> existingUser = Optional.ofNullable(this.userRepository.findByUserName(user.getUserName()));

        if(user.getPassword().equals(existingUser.get().getPassword())) {
            return "Logging in...";
        };

        return "Apologies, we do not recognise those details. Please try logging in again.";
    }
}





