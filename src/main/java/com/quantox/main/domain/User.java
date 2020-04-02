package com.quantox.main.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import com.quantox.main.securityImp.PasswordEncoderImp;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	@Email(message = "Email should be valid")
	private String username;

	@NotNull
	private String password;
	@Nullable
	private String firstName;
	@Nullable
	private String lastName;

	@Autowired
	PasswordEncoderImp pe;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Machine machine;

	public User() {
	}

	public User(Long id, @NotNull @Email(message = "Email should be valid") String username, @NotNull String password,
			String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.password = pe.encode(password);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(@NotNull @Email(message = "Email should be valid") String username, @NotNull String password,
			String firstName, String lastName) {
		super();
		this.username = username;
		this.password = pe.encode(password);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", pe=" + pe + ", machine=" + machine + "]";
	}

}
