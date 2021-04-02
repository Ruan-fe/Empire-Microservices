package com.saleservice.domain.feignclients;

import com.saleservice.domain.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;



@Component
@FeignClient(name = "authentication-service", path = "/auth/user")
public interface UserFeignClient {

	@GetMapping
	User getAuthenticated(@RequestHeader("Authorization") String bearerToken);
}