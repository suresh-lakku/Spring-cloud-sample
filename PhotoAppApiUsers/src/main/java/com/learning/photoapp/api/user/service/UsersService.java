package com.learning.photoapp.api.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.learning.photoapp.api.user.dto.UserDto;

public interface UsersService extends UserDetailsService {
	UserDto createUser(UserDto userDetails);
	UserDto getUserDetailsByEmailId(String emailId);
	UserDto getUserByUserId(String userId);
}
