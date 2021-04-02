package com.empire.authentication.domain.services;

import com.empire.authentication.configurations.exceptions.UsernameAlreadyExistException;
import com.empire.authentication.domain.entities.User;
import com.empire.authentication.domain.repositories.PermissionRepository;
import com.empire.authentication.domain.repositories.UserRepository;
import com.empire.authentication.rest.models.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException(" Username " + username + " not found");
        }
    }

    @Transactional
    public User registerUser(UserRequest userRequest) {

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new UsernameAlreadyExistException("Username already exist");
        }

        User user = userRequest.convert(permissionRepository);
        return userRepository.save(user);
    }

   public User getAuthenticated() {
        try {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
           return null;
        }
    }

}
