package com.electronic.commerce.shop.registration;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Hash entity model
 *
 */
@Entity(name = "hash")
public class Hash  {
	/**
	 * The hash itself
	 */
	@Id
	private String hashKey = generateHash();
	/**
	 * The user's email address for which the hash was generated
	 */
	private String emailAddress;
	/**
	 * No arguments constructor used for initialization from database
	 */
	public Hash() {
	}
	
	public Hash(String emailAddress) {
		super();
		this.emailAddress = emailAddress;
	}

	/**
	 * Generates a random string based on the current timestamp and a random value between 0 and 99
	 * @return a random string used as hash
	 */
	private String generateHash() {
		long timeStamp = System.currentTimeMillis();
		Random rand = new Random();
		return String.valueOf(timeStamp) + String.valueOf(rand.nextInt(100));
	}
	/**
	 * Getter
	 * @return hash
	 */
	public String getHashKey() {
		return hashKey;
	}
	/**
	 * Getter
	 * @return the user's email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
	
}