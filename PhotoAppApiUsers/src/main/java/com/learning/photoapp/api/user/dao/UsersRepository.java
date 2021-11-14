package com.learning.photoapp.api.user.dao;

import org.springframework.data.repository.CrudRepository;

import com.learning.photoapp.api.user.data.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByEmailId(String emailId);
	UserEntity findByUserId(String userId);
}
