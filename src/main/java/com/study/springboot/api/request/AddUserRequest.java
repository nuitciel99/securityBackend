package com.study.springboot.api.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class AddUserRequest {
	
	private String email;
	private String password;

}
