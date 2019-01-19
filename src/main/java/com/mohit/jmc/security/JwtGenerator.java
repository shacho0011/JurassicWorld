package com.mohit.jmc.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mohit.jmc.model.security.JwtUser;

@Component
public class JwtGenerator {

	@Value("${jwt.secret}")
	private String secretKey;

	public String generate(JwtUser jwtUser) {

		Claims claims = Jwts.claims().setSubject(jwtUser.getUsername());
		claims.put("role", jwtUser.getRole());

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		long expMillis = nowMillis + 600000;
		Date exp = new Date(expMillis);

		return Jwts.builder().setIssuedAt(now).setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey)
				.setExpiration(exp).compact();
	}
}
