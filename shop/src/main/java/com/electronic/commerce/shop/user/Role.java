package com.electronic.commerce.shop.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class representing a user's role
 * @author Sabin Theodor
 *
 */
@Entity(name = "roles")
public class Role {
	
	
	
	/**
	 * Holds the role id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer role_id;
	/**
	 * Holds the name of the role
	 */
	private String roleName;
	

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleId() {
		return role_id;
	}

	public void setRoleId(Integer roleId) {
		this.role_id = roleId;
	}

}
