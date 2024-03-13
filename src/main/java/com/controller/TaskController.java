package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserEntity;
import com.repository.UserRepository;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	UserRepository userRepo; 
	
	//get users name of task 
	@GetMapping("/users/{taskId}")
	public ResponseEntity<?> getAll(@PathVariable("taskId") Integer taskId) {

		List<UserEntity> users = userRepo.getAllUsersOfTask(taskId);
		return ResponseEntity.ok(users);
	}
	 
	

}
