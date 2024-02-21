package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.ProjectEntity;
import com.entity.UserProjectEntity;
import com.repository.ProjectRepository;
import com.repository.UserProjectRepository;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	ProjectRepository projectRepo;

	@Autowired
	UserProjectRepository userProjectRepo;

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

	@PostMapping("/assign/{userId}/{projectId}/{status}")
	public ResponseEntity<?> assignProjectUserStatus(@PathVariable("userId") Integer userId,
			@PathVariable("projectId") Integer projectId, @PathVariable("status") String status) {
		UserProjectEntity up = new UserProjectEntity();
		up.setProjectId(projectId);
		up.setUserId(userId);
		up.setStatus(status);

		userProjectRepo.save(up);
		return ResponseEntity.ok(up);
	}

	@GetMapping("/myproject/{userId}")
	public ResponseEntity<?> myProjects(@PathVariable("userId") Integer userId) {

		List<Object> allProjects = userProjectRepo.myProjects(userId);

		return ResponseEntity.ok(allProjects);

	}

}
