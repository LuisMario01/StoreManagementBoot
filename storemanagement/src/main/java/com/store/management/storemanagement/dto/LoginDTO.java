package com.store.management.storemanagement.dto;

import javax.validation.constraints.NotNull;

// Data Transfer Object to login a user of the system
public class LoginDTO {
	
	@NotNull(message="Field: username must not be null")
	private String username;
	
	@NotNull(message="Field: password must not be null")
	private String password;
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
}
