package com.electronic.commerce.shop.user;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public class CustomTokenService extends UserInfoTokenServices {

	private UserOAuthRepository userOAuthRepository;

	private String provider;

	/**
	 * Constructor
	 * 
	 * @param userInfoEndpointUrl
	 * @param clientId
	 * @param userOAuthRepository
	 */
	public CustomTokenService(String userInfoEndpointUrl, String clientId, UserOAuthRepository userOAuthRepository) {
		super(userInfoEndpointUrl, clientId);
		this.userOAuthRepository = userOAuthRepository;
	}

	/**
	 * Check if there is already a record in the database with the appId
	 * 
	 * @param appId The user's id within the application he authenticated with
	 * @return true if appId exists in the database and false otherwise
	 */
	public boolean CheckIfExists(String appId) {
		Iterable<UserOAuth> userList = new ArrayList<UserOAuth>();
		userList = userOAuthRepository.findAll();
		for (UserOAuth dbUser : userList) {
			if (dbUser.getAppId().equals(appId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Authenticates a user and if there is no record of him in the database, it
	 * inserts one
	 * 
	 * @return an authentication
	 */
	@Override
	public OAuth2Authentication loadAuthentication(String accessToken)
			throws AuthenticationException, InvalidTokenException {

		OAuth2Authentication loadAuthentication = super.loadAuthentication(accessToken);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) loadAuthentication.getUserAuthentication().getDetails();
		String id = String.valueOf(map.get("id"));
		String email = String.valueOf(map.get("email"));

		if (!CheckIfExists(id)) {
			UserOAuth userOAuth = new UserOAuth();
			userOAuth.setAppId(id);
			userOAuth.setEmail(email);
			if (this.provider.contains("github")) {
				userOAuth.setApp("Github");
				userOAuth.setName(String.valueOf(map.get("login")));
			} else if (this.provider.contains("facebook")) {
				userOAuth.setName(String.valueOf(map.get("name")));
				userOAuth.setApp("Facebook");
			} else {
				userOAuth.setName(String.valueOf(map.get("name")));
				userOAuth.setApp("Google");
			}
			userOAuthRepository.save(userOAuth);
		}
		return loadAuthentication;
	}

	/**
	 * Getter
	 * 
	 * @return provider
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * Setter
	 * 
	 * @param userInfoUri The API address used to gather data from the authenticated
	 *                    user
	 */
	public void setProvider(String userInfoUri) {
		this.provider = userInfoUri;
	}

}