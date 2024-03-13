package com.service;

import org.springframework.stereotype.Service;

@Service
public class TokenGeneratorService {

	public String geneateToken(int length) {
		String data = "ABCDEFGHIJKMNOPQRSTUVWQYZ" + "0123456789" + "abcdefghijklmnopqrstuvwxyz";
		// 0 61
		StringBuffer token = new StringBuffer();

		for (int i = 1; i <= length; i++) {
			int index = (int) (Math.random() * data.length()); // 0.985215632
			token.append(data.charAt(index));
		}
		return token.toString();
	}
}
