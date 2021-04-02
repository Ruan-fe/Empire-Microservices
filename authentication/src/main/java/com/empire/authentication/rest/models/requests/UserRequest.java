package com.empire.authentication.rest.models.requests;

import com.empire.authentication.domain.entities.Permission;
import com.empire.authentication.domain.entities.User;
import com.empire.authentication.domain.repositories.PermissionRepository;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;
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

    @NotNull(message = "The username can't be null")
    private String username;
    @NotNull(message = "The password can't be null")
    private String password;


    public User convert(PermissionRepository permissionRepository) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Permission permission = permissionRepository.findByDescription("User_default");


        return User.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .permissions(Arrays.asList(permission))
                .build();
    }

}
