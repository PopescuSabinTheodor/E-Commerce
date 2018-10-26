package com.electronic.commerce.shop;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
	
	/**
	 * Sets views
	 */
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/register").setViewName("register");
		registry.addViewController("/").setViewName("register");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/change-password").setViewName("changePassword");
	}

}
