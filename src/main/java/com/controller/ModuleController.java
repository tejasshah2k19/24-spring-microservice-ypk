package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.ModuleEntity;
import com.repository.ModuleRepository;

@RestController
@RequestMapping("/module")
public class ModuleController {

	@Autowired
	ModuleRepository moduleRepo;

	@PostMapping
	public ResponseEntity<?> addModule(@RequestBody ModuleEntity moduleEntity) {
		System.out.println(moduleEntity.getTitle());
		moduleRepo.save(moduleEntity);

		return ResponseEntity.ok(moduleEntity);
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<?> getAllModules(@PathVariable("projectId") Integer projectId) {
		List<ModuleEntity> modules = moduleRepo.findByProjectId(projectId);
		return ResponseEntity.ok(modules);
	}

	@DeleteMapping("/{moduleId}")
	public ResponseEntity<?> removeModule(@PathVariable("moduleId") Integer moduleId) {
		moduleRepo.deleteById(moduleId);
		return ResponseEntity.ok("Module Removed");
	}

}
