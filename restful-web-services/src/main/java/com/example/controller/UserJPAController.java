package com.example.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.dao.UserDaoService;
import com.example.exception.UserNotFoundException;
import com.example.model.User;
import com.example.repository.UserRepository;

@RestController
public class UserJPAController {

	@Autowired
	UserDaoService userDaoService;

	@Autowired
	UserRepository userRepo;

	@GetMapping(path = "/jpa/get/all/users")
	public List<User> getAllUsers() {
		System.out.println("Method called************");
		List<User> users = userRepo.findAll();
		return users;
	}

	@GetMapping(path = "/jpa/get/user/{id}")
	public User getUniqueUsers(@PathVariable int id) {
		User user = userDaoService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id=" + id);
		}
		return user;
	}

	@PostMapping(path = "/jpa/user/create")
	public ResponseEntity<Object> createUser(@RequestBody @Valid User user, BindingResult result) {
		User savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@DeleteMapping(path = "/jpa/user/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userDaoService.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("id=" + id);
		}
	}
}
