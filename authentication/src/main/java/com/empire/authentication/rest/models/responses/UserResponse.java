package com.empire.authentication.rest.models.responses;

import com.empire.authentication.domain.entities.User;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserResponse implements Serializable {

    private static final long serialVersionUID = -8387927312441830960L;

    private String username;

    public UserResponse(User user) {
        username = user.getUsername();
    }
}
