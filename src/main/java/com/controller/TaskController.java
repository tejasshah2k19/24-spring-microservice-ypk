package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.TaskEntity;
import com.repository.TaskRepository;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	TaskRepository taskRepo;
	
	@PostMapping("/save")
	public ResponseEntity<?> addTask(@RequestBody  TaskEntity task){
		taskRepo.save(task);
		return ResponseEntity.ok(task);
	}
	
	@GetMapping("/{moduleId}")
	public ResponseEntity<?> allTaskByModule(@PathVariable("moduleId") Integer moduleId) {
		List<TaskEntity> task = taskRepo.findByModuleId(moduleId);
		return ResponseEntity.ok(task);
	}
	
	@GetMapping("/get/{taskId}")
	public ResponseEntity<?> getTaskById(@PathVariable("taskId") Integer taskId){
		TaskEntity task  = taskRepo.findById(taskId).get();
		return ResponseEntity.ok(task);
	}
	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<?> deleteTaskById(@PathVariable("taskId") Integer taskId){
		taskRepo.deleteById(taskId);
		return ResponseEntity.ok("task removed");
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateTask(@RequestBody  TaskEntity task){
		TaskEntity dbTask = taskRepo.findById(task.getTaskId()).get();
		dbTask.setStatus(task.getStatus());
		taskRepo.save(dbTask);
		return ResponseEntity.ok(dbTask);
	}
	
	@GetMapping("/bug")
	public ResponseEntity<?> allBugTask() {
		List<TaskEntity> task = taskRepo.findByStatus("Defect");
		return ResponseEntity.ok(task);
	}
}
