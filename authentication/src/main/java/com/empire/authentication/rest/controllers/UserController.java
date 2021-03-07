package com.empire.authentication.rest.controllers;

import com.empire.authentication.domain.entities.User;
import com.empire.authentication.domain.services.UserService;
import com.empire.authentication.rest.models.requests.UserRequest;
import com.empire.authentication.rest.models.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("auth/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static String pathWithId = "/api/v1/usuario/{id}";

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRequest userRequest, UriComponentsBuilder uriBuilder) {

        User user = userService.registerUser(userRequest);

        URI uri = uriBuilder.path(pathWithId).buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserResponse(user));
    }


}
