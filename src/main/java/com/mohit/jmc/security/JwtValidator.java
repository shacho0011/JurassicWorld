package com.mohit.jmc.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mohit.jmc.model.security.JwtUser;

@Component
public class JwtValidator {

	@Value("${jwt.secret}")
	private String secretKey;

	public JwtUser validate(String token) {

		JwtUser jwtUser = null;
		try {
			Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

			jwtUser = new JwtUser();

			jwtUser.setUsername(body.getSubject());
			jwtUser.setRole((String) body.get("role"));
		} catch (Exception e) {
			System.out.println(e);
		}

		return jwtUser;
	}
}
