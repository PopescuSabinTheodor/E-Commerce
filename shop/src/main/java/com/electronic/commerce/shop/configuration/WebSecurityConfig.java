package com.electronic.commerce.shop.configuration;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.CompositeFilter;

import com.electronic.commerce.shop.user.CustomTokenService;
import com.electronic.commerce.shop.user.UserOAuthRepository;

@Configuration
@EnableOAuth2Client
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	@Autowired
	private UserOAuthRepository userOAuthRepository;

	@RequestMapping({ "/user", "/me" })
	public Principal user(Principal principal) {
		return principal;
	}

	@ResponseBody
	@RequestMapping("/users")
	public Iterable<com.electronic.commerce.shop.user.UserOAuth> dbList() {
		return userOAuthRepository.findAll();
	}

	

	@Bean
	public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(
			OAuth2ClientContextFilter filter) {
		FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	@Bean
	@ConfigurationProperties("google")
	public ClientResources google() {
		return new ClientResources();
	}

	@Bean
	@ConfigurationProperties("facebook")
	public ClientResources facebook() {
		return new ClientResources();
	}

	@Bean
	@ConfigurationProperties("github")
	public ClientResources github() {
		return new ClientResources();
	}

	/*
	 * Add filters for authentication
	 * 
	 * @return a {@link CompositeFilter}
	 */
	private Filter ssoFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		filters.add(ssoFilter(facebook(), "/login/facebook"));
		filters.add(ssoFilter(google(), "/login/google"));
		filters.add(ssoFilter(github(), "/login/github"));
		filter.setFilters(filters);
		return filter;
	}

	/*
	 * Build an authentication filter
	 * 
	 * @param client
	 * @param path
	 * @return An {@link OAuth2ClientAuthenticationProcessingFilter}
	 */
	private Filter ssoFilter(ClientResources client, String path) {
		OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
		OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(template);
		CustomTokenService tokenServices = new CustomTokenService(client.getResource().getUserInfoUri(),
				client.getClient().getClientId(), userOAuthRepository);
		tokenServices.setRestTemplate(template);
		tokenServices.setProvider(client.getResource().getUserInfoUri());
		filter.setTokenServices(tokenServices);
		return filter;
	}

	class ClientResources {

		@NestedConfigurationProperty
		private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

		@NestedConfigurationProperty
		private ResourceServerProperties resource = new ResourceServerProperties();

		public AuthorizationCodeResourceDetails getClient() {
			return client;
		}

		public ResourceServerProperties getResource() {
			return resource;
		}
	}

	/*
	 * Configure security for user interface
	 */
	@Override
	protected void configure(HttpSecurity https) throws Exception {

		https.antMatcher("/**").authorizeRequests()
				.antMatchers("/admin/**","/", "/resources/**", "/signup", "/verification/**", "/users", "/error", "/loginForm", "/login", "/login/google", "/login/facebook",
						"/login/github", "/users", "/images/**", "/*.png", "/favicon.ico", "/templates/**",
						"/static/**", "/css/**", "/js/**", "src/main/resources/**", "/**.js", "/static/css/**.css", "/static/js/**.js",
						"/**.ico")
				.permitAll().anyRequest().authenticated().and().exceptionHandling().and().formLogin()
				.loginPage("/loginForm").defaultSuccessUrl("/").permitAll().and().logout()
				.logoutSuccessUrl("/").and()
				//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
				.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class).csrf().disable();
	}
	

	
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		@SuppressWarnings("deprecation")
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
