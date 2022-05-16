package com.example.demo.login.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "wisestep_users")
public class User {

	@Id
	private String id;
	private String emailId;
	private String otpPassword;
	private boolean userLoggedIn;
	
}
