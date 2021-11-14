package com.learning.photoapp.api.user.ui.model;

import java.util.List;

public class UserResponseModel extends CreateUserResponseModel {

	private static final long serialVersionUID = -8031111477189440728L;
	
	private List<AlbumResponseModel> albums;

	public List<AlbumResponseModel> getAlbums() {
		return albums;
	}

	public void setAlbums(List<AlbumResponseModel> albums) {
		this.albums = albums;
	}
	
}
