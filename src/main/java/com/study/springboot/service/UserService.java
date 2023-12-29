package com.study.springboot.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.springboot.api.request.AddUserRequest;
import com.study.springboot.config.jwt.TokenProvider;
import com.study.springboot.entity.User;
import com.study.springboot.repository.UserRepository;

//import ch.qos.logback.core.util.Duration;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	
	public Long save(AddUserRequest dto	) {
		User user = User.builder()
						.email(dto.getEmail())
						.password(passwordEncoder.encode(dto.getPassword()))
						.build();
		return userRepository.save(user).getId();
	}

	public boolean isEmailAlreadyExists(String email) {
		
		return userRepository.existsByEmail(email);
	}

	public String login(AddUserRequest request) {
		User user = userRepository.findByEmail(request.getEmail());
		
		if(user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//			Duration expoirationTime = Duration.of
			Duration expoirationTime = Duration.ofMinutes(30);
			String token = tokenProvider.generateToken(user, expoirationTime);
			
			return "Login Successfully " + token;
		}else {
			return "Login Failed";
		}
		
	}

}
