package com.quantox.main.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quantox.main.config.CustomBasicAuthenticationFilter;
import com.quantox.main.domain.User;
import com.quantox.main.repositories.UserRepo;
import com.quantox.main.securityImp.PasswordEncoderImp;

@RestController
@RequestMapping("user/")
public class UserImpl {
	
	@Autowired
	public UserRepo userRepo;

	@Autowired
	CustomBasicAuthenticationFilter custom;
	
	@Autowired
	PasswordEncoderImp ps;

	
	public UserImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public UserImpl() {}
	
	@RequestMapping(value="login/", method= RequestMethod.POST)
	public HttpStatus login(@RequestBody @Valid String userName, @RequestBody @Valid String password) {
		User user = userRepo.login(userName, ps.encode(password));
		if(user != null) {
			return HttpStatus.OK;
		}
		
		return HttpStatus.FORBIDDEN;
	}

	@RequestMapping(value="create/", method= RequestMethod.POST)
	public User createNew(@RequestBody @Valid User user) {
		return user = userRepo.save(user);

	}

}
