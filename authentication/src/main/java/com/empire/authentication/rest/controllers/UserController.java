package com.empire.authentication.rest.controllers;

import com.empire.authentication.rest.models.requests.UserRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testeUsuario")
public class UserController {

    @GetMapping
    public UserRequest teste() {
        UserRequest userRequest = new UserRequest("ruan","123");
        return userRequest;
    }
}
