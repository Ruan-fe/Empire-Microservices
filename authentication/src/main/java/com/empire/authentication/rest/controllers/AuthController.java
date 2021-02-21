package com.empire.authentication.rest.controllers;

import java.util.HashMap;
import java.util.Map;

import com.empire.authentication.configurations.security.jwt.JwtTokenProvider;
import com.empire.authentication.domain.entities.User;
import com.empire.authentication.domain.repositories.UserRepository;
import com.empire.authentication.rest.models.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                          UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }



    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
        try {
            String username = userRequest.getUserName();
            String password = userRequest.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User user = userRepository.findByUserName(username);

            String token = "";

            if(user != null) {
                token = jwtTokenProvider.createToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username not found");
            }

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token",token);
            return ok(model);

        }catch(AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password");
        }
    }
}
