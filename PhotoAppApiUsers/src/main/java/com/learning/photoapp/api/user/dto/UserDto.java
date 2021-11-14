package com.learning.photoapp.api.user.dto;

import java.io.Serializable;
import java.util.List;

import com.learning.photoapp.api.user.ui.model.AlbumResponseModel;

public class UserDto implements Serializable {
	
	
	private static final long serialVersionUID = -4040299950933270671L;
	
	private String firstName;
	private String lastName;
	private String password;
	private String emailId;
	private String userId;
	private String encryptedPassword;
	private List<AlbumResponseModel> albums;
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
	public List<AlbumResponseModel> getAlbums() {
		return albums;
	}
	public void setAlbums(List<AlbumResponseModel> albums) {
		this.albums = albums;
	}
}
