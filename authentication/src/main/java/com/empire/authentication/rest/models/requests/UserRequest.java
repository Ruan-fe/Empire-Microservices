package com.empire.authentication.rest.models.requests;

import com.empire.authentication.domain.entities.Permission;
import com.empire.authentication.domain.entities.User;
import com.empire.authentication.domain.repositories.PermissionRepository;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.Arrays;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserRequest implements Serializable {

    private static final long serialVersionUID = -8387927312441830960L;

    private String username;
    private String password;


    public User convert(PermissionRepository permissionRepository) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Permission permission = permissionRepository.findByDescription("User_default");


        return User.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .permissions(Arrays.asList(permission))
                .build();
    }

}
