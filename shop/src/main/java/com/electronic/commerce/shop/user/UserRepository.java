package com.electronic.commerce.shop.user;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for CRUD operations on Users
 * @author Sabin Theodor
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {
	
	/**
	 * Checks if the user with the corresponding email exists in the database
	 * @param email
	 * @return true if it does, false otherwise
	 */
	boolean existsByEmailAddress(String emailAddress);
	/**
	 * Searches for a user with the corresponding email in the database
	 * @param email email address
	 * @return an {@link Optional} of type {@link User} 
	 */
	Optional<User> findOneByEmailAddress(String emailAddress);
	
}