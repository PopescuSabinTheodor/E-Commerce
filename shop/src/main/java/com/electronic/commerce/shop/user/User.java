package com.electronic.commerce.shop.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
	
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


	

public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((app == null) ? 0 : app.hashCode());
	result = prime * result + ((appId == null) ? 0 : appId.hashCode());
	result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
	result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result + ((homeAddress == null) ? 0 : homeAddress.hashCode());
	result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
	result = prime * result + ((roles == null) ? 0 : roles.hashCode());
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	result = prime * result + ((userId == null) ? 0 : userId.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (!(obj instanceof User))
		return false;
	User other = (User) obj;
	if (app == null) {
		if (other.app != null)
			return false;
	} else if (!app.equals(other.app))
		return false;
	if (appId == null) {
		if (other.appId != null)
			return false;
	} else if (!appId.equals(other.appId))
		return false;
	if (emailAddress == null) {
		if (other.emailAddress != null)
			return false;
	} else if (!emailAddress.equals(other.emailAddress))
		return false;
	if (firstName == null) {
		if (other.firstName != null)
			return false;
	} else if (!firstName.equals(other.firstName))
		return false;
	if (homeAddress == null) {
		if (other.homeAddress != null)
			return false;
	} else if (!homeAddress.equals(other.homeAddress))
		return false;
	if (lastName == null) {
		if (other.lastName != null)
			return false;
	} else if (!lastName.equals(other.lastName))
		return false;
	if (password == null) {
		if (other.password != null)
			return false;
	} else if (!password.equals(other.password))
		return false;
	if (phoneNumber == null) {
		if (other.phoneNumber != null)
			return false;
	} else if (!phoneNumber.equals(other.phoneNumber))
		return false;
	if (roles == null) {
		if (other.roles != null)
			return false;
	} else if (!roles.equals(other.roles))
		return false;
	if (status == null) {
		if (other.status != null)
			return false;
	} else if (!status.equals(other.status))
		return false;
	if (userId == null) {
		if (other.userId != null)
			return false;
	} else if (!userId.equals(other.userId))
		return false;
	return true;
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

public Set<Role> getRoles() {
	return roles;
}

public void setRoles(Set<Role> roles) {
	this.roles = roles;
}


	
}
