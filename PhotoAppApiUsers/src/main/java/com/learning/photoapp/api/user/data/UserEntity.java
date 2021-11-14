package com.learning.photoapp.api.user.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -5007997966841232282L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, name = "firstName", length = 50)
	private String firstName;
	@Column(nullable = false, name = "lastName", length = 50)
	private String lastName;
	@Column(nullable = false, name = "emailId", length = 120, unique = true)
	private String emailId;
	@Column(nullable = false, name = "userId", length = 150, unique = true)
	private String userId;
	@Column(nullable = false, name = "encryptedPassword", length = 250, unique = true)
	private String encryptedPassword;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
}
