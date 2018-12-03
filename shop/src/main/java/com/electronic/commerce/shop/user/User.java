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
	private Long userId;
	
	/**
	 * User's first name
	 */
	private String firstName;
	
	/**
	 * User's last name
	 */
	private String lastName;
	
	/**
	 * User's email address
	 */
	private String emailAddress;
	
	/**
	 * User's role
	 */
	private Integer roleId;
	
	/**
	 * User's password
	 */
	private String password;
	/**
	 * User's phone number
	 */
	private String phoneNumber;
	
	/**
	 * User's home address
	 */
	private String homeAddress;
	
	/**
	 * The status of the account
	 */
	private String status;
	
	/**
	 * User's app id
	 */
	private String appId;
	
	/**
	 * The app which the user logged with
	 */
	private String app;
	


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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int role) {
		this.roleId = role;
	}
	

public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

/**
 * Overridden hashCode method for User object
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
	result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
	result = prime * result + ((homeAddress == null) ? 0 : homeAddress.hashCode());
	result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	result = prime * result + ((userId == null) ? 0 : userId.hashCode());
	return result;
}

public String getAppId() {
	return appId;
}

public void setAppId(String appId) {
	this.appId = appId;
}

public String getApp() {
	return app;
}

public void setApp(String app) {
	this.app = app;
}
	
}
