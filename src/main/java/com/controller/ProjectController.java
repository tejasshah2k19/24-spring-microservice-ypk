package com.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.dto.ProjectDto;
import com.entity.UserEntity;
import com.repository.UserRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;

@Tag(name = "Project", description = "Communicate with Micro-Project")
@RestController
@RequestMapping("/project")
public class ProjectController {

	@Value("${google.project.url}")
	String projectUrl;

	@Autowired
	UserRepository userRepo;

	@GetMapping("/all")
	public ResponseEntity<?> getAllProjects() {

		WebClient webClient = WebClient.create(projectUrl);

		Flux<ProjectDto> myObjectsFlux = webClient.get().uri("/projects/all").accept(MediaType.APPLICATION_JSON)
				.retrieve().bodyToFlux(ProjectDto.class);

		// Now you can collect these objects into a list if needed
		List<ProjectDto> projects = myObjectsFlux.collectList().block();

		HashMap<String, Object> hm = new HashMap<>();

		hm.put("data", projects);
		return ResponseEntity.ok(hm); // all projects

	}

	@PostMapping("/save")
	public ResponseEntity<?> saveProject(@RequestBody ProjectDto project) {
		System.out.println(project.getTitle());
		WebClient webClient = WebClient.create(projectUrl);

		webClient.post().uri("/projects/save").body(BodyInserters.fromValue(project)).retrieve()
				.bodyToMono(String.class).subscribe(response -> {
					// Handle the response
					System.out.println("Response: " + response);
				}, error -> {
					// Handle any exceptions
					System.err.println("An error occurred: " + error.getMessage());
				});
		return ResponseEntity.ok(project);

	}

	@GetMapping("/users/{projectId}")
	public ResponseEntity<?> getAll(@PathVariable("projectId") Integer projectId) {

		List<UserEntity> users = userRepo.getAllUsersOfProject(projectId);
		return ResponseEntity.ok(users);
	}

	@GetMapping("/team/{projectId}")
	public ResponseEntity<?> getAllProjectMember(@PathVariable("projectId") Integer projectId) {

		WebClient webClient = WebClient.create(projectUrl);
		Flux<UserEntity> myObjectsFlux = webClient.get().uri("/userproject/" + projectId)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(UserEntity.class);
		// Now you can collect these objects into a list if needed
		List<UserEntity> users = myObjectsFlux.collectList().block();
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("data", users);
		return ResponseEntity.ok(hm); // all projects

	}
}
