package com.electronic.commerce.shop.registration;

import java.io.IOException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.electronic.commerce.shop.mailer.EmailService;
import com.electronic.commerce.shop.mailer.Mail;
import com.electronic.commerce.shop.mailer.MailBuilder;
import com.electronic.commerce.shop.user.User;
import com.electronic.commerce.shop.user.UserDto;
import com.electronic.commerce.shop.user.UserRepository;
import com.electronic.commerce.shop.user.UserService;
import com.electronic.commerce.shop.user.UserValidation;

import freemarker.template.TemplateException;

/**
 * Rest controller for registration
 * @author Sabin Theodor
 *
 */
@RestController
public class RegistrationController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private HashRepository hashRepository;
	@Autowired
	private MailBuilder mailBuilder;
	
	
	
	@RequestMapping(value = "/users/{emailAddress}", method = RequestMethod.GET)
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
			//MailBuilder mailBuilder = new MailBuilder();
			User user = userService.getFormUser(userDto);
			Mail mail = mailBuilder.build(user);
			userRepository.save(user);
			try {
				emailService.sendSimpleMessage(mail, "email-template.ftl");
			} catch (MessagingException | IOException | TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return user.getEmailAddress();
		}
		
		
	}
	
	
	/**
	 * Rest endpoint that handles a GET request, receives the verification hash sent
	 * in the user's email and checks if there is a record of it in the database. If
	 * there is, the user is validated.
	 * 
	 * @param hashValue unique string used to identify the user to be activated
	 * @return true if the hash is correct, false otherwise.
	 */
	@Transactional
	@RequestMapping(value = "/verification/{hash}", method = RequestMethod.GET)
	public RedirectView emailVerification(@PathVariable(value = "hash") String hashKey) {
		RedirectView redirectView = new RedirectView();
		// Stores in userId the hash's corresponding user's ID.
		Optional<Hash> hashOpt = hashRepository.findById(hashKey);
		Hash hash = hashOpt.get();
		// Handles the case in which there's no user email address corresponding to the hash
		if (hash != null) {
			// Selects the user based on its email address 
			// inserts it into the database and deletes its hash
			Optional<User> x = userRepository.findOneByEmailAddress(hash.getEmailAddress());
			User user = x.get();
			if (user != null) {
				hashRepository.delete(hash);
				user.setStatus("Approved");
				userRepository.save(user);
			}
			
		    redirectView.setUrl("https://localhost:8083/loginForm?activated");
		    return redirectView;
		} else {
			redirectView.setUrl("https://www.yahoo.com");
			return redirectView;
		}
	}
	
	
	
	 @RequestMapping(value="/admin", method = RequestMethod.GET)
	    public ModelAndView home(){
	        ModelAndView modelAndView = new ModelAndView();
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        modelAndView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmailAddress() + ")");
	        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
	        modelAndView.setViewName("admin");
	        return modelAndView;
	    }
	
}