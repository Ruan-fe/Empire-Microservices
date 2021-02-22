package com.empire.authentication.domain.services;

import com.empire.authentication.configurations.security.jwt.JwtTokenProvider;
import com.empire.authentication.domain.entities.User;
import com.empire.authentication.domain.repositories.UserRepository;
import com.empire.authentication.rest.models.requests.UserRequest;
import com.empire.authentication.rest.models.responses.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<AuthResponse> login(UserRequest userRequest) {
        try {
            String username = userRequest.getUsername();
            String password = userRequest.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User user = userRepository.findByUsername(username);
            String token = jwtTokenProvider.createToken(username, user.getRoles());

            AuthResponse authResponse = AuthResponse
                    .builder()
                    .username(username)
                    .token(token)
                    .build();

            return ResponseEntity.ok(authResponse);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
