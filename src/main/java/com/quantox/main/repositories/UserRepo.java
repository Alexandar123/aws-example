package com.quantox.main.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.quantox.main.domain.User;

public interface UserRepo extends CrudRepository<User, Long> {
	
	@Query("SELECT * FROM User WHERE username = ?1 AND password = ?2")
	User login(String username, String password);

}
