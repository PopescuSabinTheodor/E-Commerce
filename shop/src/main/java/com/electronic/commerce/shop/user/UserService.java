package com.electronic.commerce.shop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserValidation checkEmailAddress(String emailAddress) {
		UserValidation userValidation = new UserValidation();
		if (userRepository.existsByEmailAddress(emailAddress)) {
			userValidation.setEmailExists(true);
		}
		return userValidation;
	}

}
