package com.quantox.main.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomBasicAuthenticationFilter extends BasicAuthenticationFilter {

	@Autowired
	public CustomBasicAuthenticationFilter(final AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void onSuccessfulAuthentication(final javax.servlet.http.HttpServletRequest request,
			final javax.servlet.http.HttpServletResponse response, final Authentication authResult) {

		String token = JWTAuth.createJWT(UUID.randomUUID().toString(), "Issuer", "Token", 500000);
		try {
			FileWriter file = new FileWriter("token.txt");
			file.write(token);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setHeader("X-Auth-Token", token);

	}

}