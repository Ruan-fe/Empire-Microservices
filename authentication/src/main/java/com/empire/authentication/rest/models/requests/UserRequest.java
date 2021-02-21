package com.empire.authentication.rest.models.requests;

import lombok.*;

import java.io.Serializable;

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

}
