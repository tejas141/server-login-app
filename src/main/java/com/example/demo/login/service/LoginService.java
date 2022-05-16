package com.example.demo.login.service;

public interface LoginService {

	String sendOtpToEmail(String emailId);
	
	String loginToApp(String emailId, String otp);
	
	String logOutUser(String emailId);
}
