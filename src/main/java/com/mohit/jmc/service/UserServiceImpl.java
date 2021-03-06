package com.mohit.jmc.service;

import com.mohit.jmc.converter.UserConverter;
import com.mohit.jmc.dto.UserRegDto;
import com.mohit.jmc.model.User;
import com.mohit.jmc.repository.UserRepository;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserConverter userConverter;

	@Override
	public User getUserByUsername(String username) {
		User user = null;

		try {
			user = userRepository.findByEmail(username);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User createOrUpdateUser(User user, UserRegDto userRegDto) {

		try {

			user = userConverter.overwriteUser(userRegDto, user);
			user = userRepository.save(user);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User getUserDetails(Principal principal) {
		User user = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof UsernamePasswordAuthenticationToken) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			user = userRepository.findByEmail(userDetails.getUsername());
		}

		return user;
	}
}
