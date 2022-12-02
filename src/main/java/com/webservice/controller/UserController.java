package com.webservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webservice.entity.UserEntity;
import com.webservice.service.UserService;
import com.webservice.service.util.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<UserEntity> getUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public UserEntity getOneUser(@PathVariable int id) {
		UserEntity entity = userService.findOne(id);
		if (entity == null) {
			throw new UserNotFoundException("id:" + id);
		}
		return entity;
	}
	
	@PostMapping("/users/create")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity entity) {
		UserEntity userEntity = userService.createUser(entity);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(userEntity.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/users/delete/{id}")
	public void deleteOne(@PathVariable int id) {
		userService.deleteOne(id);
	}
}
