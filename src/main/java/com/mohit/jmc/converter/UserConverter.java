package com.mohit.jmc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mohit.jmc.dto.UserRegDto;
import com.mohit.jmc.model.Role;
import com.mohit.jmc.model.User;
import com.mohit.jmc.repository.RoleRepository;

@Component
@Scope("prototype")
public class UserConverter {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	RoleRepository roleRepository;

	public User overwriteUser(UserRegDto dto, User user) {

		if (dto.getFirstName() != null) {
			user.setFirstName(dto.getFirstName());
		}
		if (dto.getLastName() != null) {
			user.setLastName(dto.getLastName());
		}
		if (dto.getEmail() != null) {
			user.setEmail(dto.getEmail());
		}
		if (dto.getPassword() != null) {
			String password = passwordEncoder.encode(dto.getPassword());
			user.setPassword(password);
		}

		Role role = null;
		try {

			role = roleRepository.findOne(2L); //by default they are user
			user.setRole(role);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

}
