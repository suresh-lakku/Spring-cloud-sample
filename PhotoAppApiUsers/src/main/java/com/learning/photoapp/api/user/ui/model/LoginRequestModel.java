package com.learning.photoapp.api.user.ui.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginRequestModel implements Serializable {

	private static final long serialVersionUID = 8199568380770360973L;
	
	@NotNull(message="email cannot be null")
	@Email
	private String email;
	
	@NotBlank(message="Password cannot be null")
	@Size(min = 8, max=16, message="password must not be less than 8 and more than 16 characters")
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
