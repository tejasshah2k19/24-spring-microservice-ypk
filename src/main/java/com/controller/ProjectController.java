package com.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin(origins = "*")
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

	@PostMapping("/assign")
	public ResponseEntity<?> assignProjectUserStatus(@RequestBody UserProjectEntity up) {

		userProjectRepo.save(up);
		return ResponseEntity.ok(up);
	}

	@GetMapping("/myproject/{userId}")
	public ResponseEntity<?> myProjects(@PathVariable("userId") Integer userId) {

		List<ProjectEntity> allProjects = projectRepo.myProjects(userId);

		return ResponseEntity.ok(allProjects);
	}
	
	
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable("projectId") Integer projectId){
		projectRepo.deleteById(projectId);
		return ResponseEntity.ok("project removed");
	}

	@GetMapping("status") //dashboard status 
	public ResponseEntity<?> getDSStatus()
	{
		List<ProjectEntity> inProgress = projectRepo.findByStatus("InProgress");
		List<ProjectEntity> completed = projectRepo.findByStatus("Completed");
		List<ProjectEntity> pipeline = projectRepo.findByStatus("Pipeline");
		List<ProjectEntity> notStarted = projectRepo.findByStatus("NotStarted");
		Integer count = userProjectRepo.getUserCount();
		Integer devCount = userProjectRepo.getDeveloperCount();
		
		HashMap<String, Integer> hm = new HashMap<>();
		hm.put("inProgress", inProgress.size());
		hm.put("completed", completed.size());
		hm.put("pipeline", pipeline.size());
		hm.put("notStarted", notStarted.size());
		hm.put("count", count);
		hm.put("devCount",devCount);
		
		return ResponseEntity.ok(hm);
		
	}
	
	//user project 
	
}
