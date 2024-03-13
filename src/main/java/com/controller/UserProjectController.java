package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserProjectEntity;
import com.repository.UserProjectRepository;

@RestController
@RequestMapping("/userproject")
public class UserProjectController {

	@Autowired
	UserProjectRepository userProjectRepo; 
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getAllTeamMembersByProjectId(@PathVariable("projectId") Integer projectId){
		
		List<UserProjectEntity> allMember = userProjectRepo.findByProjectId(projectId);
		return ResponseEntity.ok(allMember);
	}
}
