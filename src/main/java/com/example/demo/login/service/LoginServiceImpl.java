package com.example.demo.login.service;

import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.demo.login.domain.User;
import com.example.demo.login.exception.LoginException;
import com.example.demo.login.repo.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public String sendOtpToEmail(String emailId) {
		if (emailId == "") {
			emailId = null;
		}
		try {
			Assert.notNull(emailId, "Please provide email ID");
			Random random = new Random();
			String otp = String.format("%04d", random.nextInt(10000));
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(emailId);
			mailMessage.setFrom("teju2k22@gmail.com");
			mailMessage.setSubject("OTP Password for login");
			mailMessage.setText("Hi, your OTP for login is "+ otp);
			mailSender.send(mailMessage);
			User user = userRepo.findByEmailId(emailId);
			if (Objects.nonNull(user)) {
				user.setOtpPassword(otp);
				userRepo.save(user);
			} else {
				User newUser = new User();
				newUser.setEmailId(emailId);
				newUser.setOtpPassword(otp);
				userRepo.save(newUser);
			}
			return "Otp sent to "+emailId;
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@Override
	public String loginToApp(String emailId, String otp) {
		if (otp == "") {
			otp = null;
		}
		if (emailId == "") {
			emailId = null;
		}
		try {
			Assert.notNull(emailId, "Please provide user email id");
			Assert.notNull(otp, "Please enter the otp");
			User user = userRepo.findByEmailId(emailId);
			Assert.notNull(user, "User with given email does not exist");
			if (!user.isUserLoggedIn()) {
				if (otp.equals(user.getOtpPassword())) {
					user.setUserLoggedIn(true);
					userRepo.save(user);
				} else {
					throw new LoginException("OTP entered is not correct");
				}
			} else {
				throw new LoginException("The user with emailID " + emailId + " is already logged in");
			}
			return "Login successful";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@Override
	public String logOutUser(String emailId) {
		try {
			User user = userRepo.findByEmailId(emailId);
			Assert.notNull(user, "User with given email does not exist");
			user.setOtpPassword(null);
			user.setUserLoggedIn(false);
			userRepo.save(user);
			return "Logout Successful";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
