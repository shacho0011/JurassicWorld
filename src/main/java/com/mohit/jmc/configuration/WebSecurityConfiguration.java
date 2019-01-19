package com.mohit.jmc.configuration;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mohit.jmc.security.JwtAuthenticationEntryPoint;
import com.mohit.jmc.security.JwtAuthenticationProvider;
import com.mohit.jmc.security.JwtAuthenticationTokenFilter;
import com.mohit.jmc.security.JwtSuccessHandler;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationProvider authenticationProvider;
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilter() {
		JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("**/api/**")
			.authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(entryPoint).and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		http.headers().cacheControl();

	}

}
