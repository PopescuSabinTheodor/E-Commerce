package com.electronic.commerce.shop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User service
 * @author Sabin Theodor
 *
 */
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
		user.setPassword(userDto.getPassword());
		user.setHomeAddress("");
		user.setPhoneNumber("");
		user.setRoleId(1);
		user.setStatus("Unapproved");
		return user;
	}
}
