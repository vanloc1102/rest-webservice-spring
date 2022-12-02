package com.webservice.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.webservice.entity.UserEntity;
import com.webservice.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	private static List<UserEntity> users = new ArrayList<>();
	
	private static int idCount = 0;
	
	static {
		users.add(new UserEntity(++idCount, "Luffy", LocalDate.now().minusYears(30)));
		users.add(new UserEntity(++idCount, "Zoro", LocalDate.now().minusYears(25)));
		users.add(new UserEntity(++idCount, "Sanji", LocalDate.now().minusYears(20)));
	}
	
	@Override
	public List<UserEntity> getAllUsers() {
		return users;
	}

	@Override
	public UserEntity findOne(int id) {
		Predicate<? super UserEntity> predicate = user -> user.getId().equals(id);
		Optional<UserEntity> user = users.stream().filter(predicate).findFirst();
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Override
	public UserEntity createUser(@Valid UserEntity entity) {
		entity.setId(++idCount);
		users.add(entity);
		return entity;
	}

	@Override
	public void deleteOne(int id) {
		Predicate<? super UserEntity> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
}
