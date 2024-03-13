package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserTaskEntity;
import com.repository.TaskRepository;
import com.repository.UserTaskRepository;

@RestController
@RequestMapping("/usertask")
public class UserTaskController {

	@Autowired
	UserTaskRepository userTaskRepo;

	@Autowired
	TaskRepository taskRepo;

	@PostMapping("/assign")
	public ResponseEntity<?> assignTask(@RequestBody UserTaskEntity userTaskEntity) {
		userTaskRepo.save(userTaskEntity);
		return ResponseEntity.ok(userTaskEntity);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllUserTask() {

		return ResponseEntity.ok(userTaskRepo.findAll());
	}

	@GetMapping("/mytask/{userId}")
	public ResponseEntity<?> getAllTaskByUserId(@PathVariable("userId") Integer userId) {

		
		return ResponseEntity.ok(taskRepo.myTask(userId));
	}


	@GetMapping("/mymoduletask/{userId}/{moduleId}")
	public ResponseEntity<?> getAllTaskByUserIdAndModuleId(@PathVariable("userId") Integer userId,@PathVariable("moduleId") Integer moduleId) {
		
		return ResponseEntity.ok(taskRepo.myModuleTask(userId,moduleId));
	}
	
}
