package com.empire.authentication;

import com.empire.authentication.domain.entities.Permission;
import com.empire.authentication.domain.entities.User;
import com.empire.authentication.domain.repositories.PermissionRepository;
import com.empire.authentication.domain.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository,
						   BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			initUsers(userRepository, permissionRepository, passwordEncoder);
		};

	}

	private void initUsers(UserRepository userRepository, PermissionRepository permissionRepository,
						   BCryptPasswordEncoder passwordEncoder) {

		Permission permission = null;
		Permission findPermission = permissionRepository.findByDescription("Admin");
		if (findPermission == null) {
			permission = new Permission();
			permission.setDescription("Admin");
			permission = permissionRepository.save(permission);
		} else {
			permission = findPermission;
		}

		User admin = new User();
		admin.setUsername("ruan");
		admin.setIsAccountNonExpired(true);
		admin.setIsAccountNonLocked(true);
		admin.setIsCredentialsNonExpired(true);
		admin.setIsEnabled(true);
		admin.setPassword(passwordEncoder.encode("123456"));
		admin.setPermissions(Arrays.asList(permission));

		User find = userRepository.findByUsername("ruan");
		if (find == null) {
			userRepository.save(admin);
		}
	}
}
