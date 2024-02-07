package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserEntity;
import com.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserRepository userRepo;

	@PostMapping("/save")
	public ResponseEntity<?> addUser(@RequestBody UserEntity user) {
		userRepo.save(user);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userRepo.findAll());
	}

	@GetMapping("/get/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok(userRepo.findById(userId));
	}
}
