package com.webservice.service;

import java.util.List;

import com.webservice.entity.UserEntity;

public interface UserService {

	List<UserEntity> getAllUsers();
	
	UserEntity findOne(int id);
	
	UserEntity createUser(UserEntity entity);
	
	void deleteOne(int id);
}
