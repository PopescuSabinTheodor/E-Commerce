package com.electronic.commerce.shop.user;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
		user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		user.setHomeAddress("");
		user.setPhoneNumber("");
		Role userRole = roleRepository.findByRoleName("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		user.setStatus("Unapproved");
		return user;
	}

	public User findUserByEmail(String emailAddress) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepository.findOneByEmailAddress(emailAddress);
		return optionalUser.get();
	}
}
