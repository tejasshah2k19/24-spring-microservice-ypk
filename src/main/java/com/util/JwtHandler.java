package com.util;

import java.security.KeyPairGenerator;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHandler {

	public String generateToken(String email) {
		Date today = new Date();
		Date after2Day = new Date(today.getTime() + (1000 * 60 * 60 * 24 * 2));
		KeyPairGenerator keyPairGenerator = null;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Jwts.builder().setSubject(email).setIssuedAt(today).setExpiration(after2Day)
				.signWith(keyPairGenerator.genKeyPair().getPrivate(), SignatureAlgorithm.HS256).compact();
	}
}
