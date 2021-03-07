package com.empire.authentication.rest.controllers;


import com.empire.authentication.domain.services.AuthService;
import com.empire.authentication.rest.models.requests.UserRequest;
import com.empire.authentication.rest.models.responses.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth/login")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid UserRequest userRequest) {
        return authService.login(userRequest);
    }


}
