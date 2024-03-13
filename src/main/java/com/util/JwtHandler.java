package com.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHandler {

	public String generateToken(String email) {
		Date today = new Date();
		Date after2Day = new Date(today.getTime() + (1000 * 60 * 60 * 24 * 2));
		SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

		return Jwts.builder().setSubject(email).setIssuedAt(today).setExpiration(after2Day).signWith(secretKey)
				.compact();
	}
}
