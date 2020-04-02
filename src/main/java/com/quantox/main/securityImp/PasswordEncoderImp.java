package com.quantox.main.securityImp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.quantox.main.repositories.PasswordEncoder;

public class PasswordEncoderImp implements PasswordEncoder {

	@Override
	public String encode(String rawPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		return encoder.encode(rawPassword);
	}

	@Override
	public boolean matches(String rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return false;
	}

}
