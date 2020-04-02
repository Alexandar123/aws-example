package com.quantox.main.repositories;

public interface PasswordEncoder {

	String encode(String rawPassword);

	boolean matches(String rawPassword, String encodedPassword);
}
