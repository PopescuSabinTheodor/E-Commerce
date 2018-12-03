package com.electronic.commerce.shop;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC Configuration class
 * @author Sabin Theodor
 *
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
	
	/**
	 * Sets views
	 */
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/register").setViewName("register");
		registry.addViewController("/").setViewName("register");
		registry.addViewController("/loginForm").setViewName("loginForm");
		registry.addViewController("/change-password").setViewName("changePassword");
		registry.addViewController("/admin").setViewName("admin");
	}

}
