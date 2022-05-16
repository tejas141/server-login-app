package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.login.service.LoginService;

@RestController
@RequestMapping("login")
@CrossOrigin("*")
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@GetMapping("/send-otp-mail")
	public ResponseEntity<String> sendOtpEmail(@RequestParam("emailId") String emailId) {
		return ResponseEntity.ok().body(loginService.sendOtpToEmail(emailId));
	}
	
	@GetMapping
	public ResponseEntity<String> login(@RequestParam("emailId") String emailId, @RequestParam("otp") String otp) {
		return ResponseEntity.ok().body(loginService.loginToApp(emailId, otp));
	}
	
	@GetMapping("/logout-user")
	public ResponseEntity<String> logout(@RequestParam("emailId") String emailId) {
		return ResponseEntity.ok().body(loginService.logOutUser(emailId));
	}
}
