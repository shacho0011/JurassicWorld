package com.mohit.jmc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.jmc.dto.UserLoginDto;
import com.mohit.jmc.dto.UserRegDto;
import com.mohit.jmc.model.User;
import com.mohit.jmc.model.security.JwtUser;
import com.mohit.jmc.security.JwtGenerator;
import com.mohit.jmc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtGenerator jwtGenerator;

	@PostMapping("/login")
	ResponseEntity<Object> getUserByUsername(@RequestBody String requestData) {
		ResponseEntity<Object> responseEntity = null;

		try {

			UserLoginDto userLoginDto = new ObjectMapper().readValue(requestData, UserLoginDto.class);
			User user = null;
			if (userLoginDto.getEmail() != null && userLoginDto.getPassword() != null) {
				user = userService.getUserByUsername(userLoginDto.getEmail());
				if (user != null) {
					if (passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
						JwtUser jwtUser = new JwtUser();
						jwtUser.setUsername(user.getEmail());
						jwtUser.setRole(user.getRole().getName());
						HttpHeaders headers = new HttpHeaders();
						headers.add("token", jwtGenerator.generate(jwtUser));
						responseEntity = new ResponseEntity<>("Login successful", headers, HttpStatus.OK);
					} else {
						responseEntity = new ResponseEntity<>("Password mismatch!", null, HttpStatus.NOT_FOUND);
					}
				} else {
					responseEntity = new ResponseEntity<>("User not found!", null, HttpStatus.NOT_FOUND);
				}
			} else {
				responseEntity = new ResponseEntity<>("Request error", null, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {

			responseEntity = new ResponseEntity<>("Internal server error!", null, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();

		}

		return responseEntity;
	}

	@PostMapping("/register")
	public ResponseEntity<Object> userRegistration(@RequestBody String requestData) {
		ResponseEntity<Object> responseEntity = null;

		try {

			UserRegDto userRegDto = new ObjectMapper().readValue(requestData, UserRegDto.class);
			User user = null;
			user = userService.getUserByUsername(userRegDto.getEmail());
			if (user != null) {
				responseEntity = new ResponseEntity<>("Already registered", null, HttpStatus.BAD_REQUEST);
			} else {
				user = new User();
				user = userService.createOrUpdateUser(user, userRegDto);
				responseEntity = new ResponseEntity<>("Registration successful", null, HttpStatus.OK);
			}

		} catch (Exception e) {

			responseEntity = new ResponseEntity<>("Internal server error!", null, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();

		}

		return responseEntity;
	}

}
