package com.learning.photoapp.api.user.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CreateUserRequestModel {

	@NotNull(message="First name cannot be null")
	@Size(min = 2, message="First Name must not be less than two characters")
	private String firstName;
	
	@NotBlank(message="Last name cannot be null")
	@Size(min = 2, message="Last Name must not be less than two characters")
	private String lastName;
	
	@NotBlank(message="Password cannot be null")
	@Size(min = 8, max=16, message="password must not be less than 8 and more than 16 characters")
	private String password;
	
	@NotNull(message="email cannot be null")
	@Email
	private String emailId;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
