package com.reka.lakatos.angularchat.controller;

import com.reka.lakatos.angularchat.document.ChatUser;
import com.reka.lakatos.angularchat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/newuser")
    public ResponseEntity addNewUser(@RequestBody ChatUser newUser) {
        try {
            userService.registerNewUser(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registration was successful.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This email is already registered.");
        }
    }

}
