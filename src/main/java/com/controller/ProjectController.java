package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.ProjectEntity;
import com.repository.ProjectRepository;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	ProjectRepository projectRepo;

	@PostMapping("/save")
	public ResponseEntity<?> addUser(@RequestBody ProjectEntity project) {
		projectRepo.save(project);
		return ResponseEntity.ok(project);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllProjects() {
		return ResponseEntity.ok(projectRepo.findAll());
	}

	@GetMapping("/get/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable("projectId") Integer projectId) {
		return ResponseEntity.ok(projectRepo.findById(projectId));
	}
}
