package com.electronic.commerce.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application class
 * Application runner class
 * @author Sabin Theodor
 *
 */

@SpringBootApplication
@ComponentScan({"com.electronic.commerce.shop.configuration", "com.electronic.commerce.shop",
	"com.electronic.commerce.shop.mailer", "com.electronic.commerce.shop.registration",
	"electronic.commerce.shop.user", "com.electronic.commerce.shop.product"})

public class Shop {
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Shop.class, args);
		
	}

}
