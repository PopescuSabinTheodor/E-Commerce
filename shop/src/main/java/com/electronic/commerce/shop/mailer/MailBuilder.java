package com.electronic.commerce.shop.mailer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electronic.commerce.shop.registration.Hash;
import com.electronic.commerce.shop.registration.HashRepository;
import com.electronic.commerce.shop.user.User;

@Service
public class MailBuilder {
	@Autowired
	private HashRepository hashRepository;
	
	public Mail build(User user) {
		
		Mail mail = new Mail();
		Hash hash = new Hash(user.getEmailAddress());
		
		
		mail.setFrom("TechEmporium");
		mail.setTo(user.getEmailAddress());
		mail.setSubject("Activation email");
		Map<String, String> model = new HashMap<String, String>();
		model.put("name", user.getFirstName() + " " + user.getLastName());
		model.put("signature", "https://www.tech-emporium.com");
		model.put("hash", hash.getHashKey());
		model.put("link", "verification");
		mail.setModel(model);
		
		hashRepository.save(hash);
		return mail;
	}
}
