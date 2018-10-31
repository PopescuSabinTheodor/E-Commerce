package com.electronic.commerce.shop.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.electronic.commerce.shop.user.User;
import com.electronic.commerce.shop.user.UserDto;
import com.electronic.commerce.shop.user.UserRepository;
import com.electronic.commerce.shop.user.UserService;
import com.electronic.commerce.shop.user.UserValidation;

@RestController
public class RegistrationController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/users/{email}", method = RequestMethod.GET)
	@ResponseBody
	public UserValidation checkUserRegistration(@PathVariable(value = "emailAddress") String emailAddress) {
		return userService.checkEmailAddress(emailAddress);
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String registerUser(@RequestBody UserDto userDto) {
		if (userDto == null) {
			return "Failed";
		}
		else
		{
			User user = userService.getFormUser(userDto);
			userRepository.save(user);
			return user.getEmailAddress();
		}
		
		
	}
}