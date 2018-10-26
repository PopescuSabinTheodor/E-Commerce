package com.electronic.commerce.shop.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class representing a user
 * @author Sabin Theodor
 *
 */
@Entity(name="users")
public class User {
	
	/**
	 * A user's unique identifier
	 * Auto generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	/**
	 * User's first name
	 */
	private String firstName;
	
	/**
	 * User's last name
	 */
	private String lastName;
	
	/**
	 * User's phone number
	 */
	private String phoneNumber;
	
	/**
	 * User's email address
	 */
	private String emailAddress;
	
	/**
	 * User's home address
	 */
	private String homeAddress;
	
	/**
	 * User's role
	 */
	private int roleId;
	

	public long getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public int getRole() {
		return roleId;
	}

	public void setRole(int role) {
		this.roleId = role;
	}
	
}
