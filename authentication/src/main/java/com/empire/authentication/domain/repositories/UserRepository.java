package com.empire.authentication.domain.repositories;

import com.empire.authentication.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String userName);

}
