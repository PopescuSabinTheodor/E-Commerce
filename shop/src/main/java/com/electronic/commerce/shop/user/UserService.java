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
	
	public User getFormUser(UserDto userDto) {
		
		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmailAddress(userDto.getEmailAddress());
		System.out.println(userDto.getEmailAddress());
		user.setHomeAddress("");
		user.setPhoneNumber("");
		user.setRole(1);
		return user;
	}
}
