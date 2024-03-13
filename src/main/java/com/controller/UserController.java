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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users", description = "User Api")
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

	@Operation(summary = "Fetch all users", description = "fetches all users entities and their data from data source")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })

	@GetMapping("/all")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userRepo.findAll());
	}

	@GetMapping("/get/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok(userRepo.findById(userId));
	}
	
	


}
