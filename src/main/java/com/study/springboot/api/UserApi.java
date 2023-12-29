package com.study.springboot.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.AddUserRequest;
import com.study.springboot.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApi {
	
	private final UserService userService;
	
	@Operation(
			summary = "회원가입",
			description = "User Join"
			)
	@PostMapping("/api/join")
	public String join(
			@RequestBody AddUserRequest request
			) {
		String email = request.getEmail();
		if(userService.isEmailAlreadyExists(email)) {
			return "Already Email Exists";
		}
		Long userId = userService.save(request);
		return "Completed Create Email" + userId;
	}
	
	@PostMapping("/api/login")
	public String login(
			@RequestBody AddUserRequest request
			) {
		return userService.login(request);
	}

}
