package com.sagitta.userservice.rest.client;

import com.sagitta.userservice.greeting.domain.ReqresUserDto;
import com.sagitta.userservice.reqres.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient (name = "REQRES-SERVICE", url = "https://reqres.in/api")
public interface ReqresClient {
	
	@GetMapping ("/users/2")
	ApiResponse<ReqresUserDto, String> getUser();
}
