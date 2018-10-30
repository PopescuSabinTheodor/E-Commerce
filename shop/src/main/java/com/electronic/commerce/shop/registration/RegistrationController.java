package com.electronic.commerce.shop.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.electronic.commerce.shop.user.UserService;
import com.electronic.commerce.shop.user.UserValidation;

@RestController
public class RegistrationController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users/{email}", method = RequestMethod.GET)
	@ResponseBody
	public UserValidation checkUserRegistration(@PathVariable(value = "emailAddress") String emailAddress) {
		return userService.checkEmailAddress(emailAddress);
	}
}