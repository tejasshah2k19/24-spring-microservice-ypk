package com.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.dto.LoginDto;
import com.dto.ProjectDto;
import com.entity.UserEntity;
import com.repository.UserRepository;
import com.util.JwtHandler;

import reactor.core.publisher.Flux;

@RestController
public class SessionController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	JwtHandler jwt;

	@Value("${google.project.url}")
	String projectUrl;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

		UserEntity user = userRepo.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginDto);
		} else {

			WebClient webClient = WebClient.create(projectUrl);

			Flux<ProjectDto> myObjectsFlux = webClient.get().uri("/projects/all").accept(MediaType.APPLICATION_JSON)
					.retrieve().bodyToFlux(ProjectDto.class);

			// Now you can collect these objects into a list if needed
			List<ProjectDto> projects = myObjectsFlux.collectList().block();

			String token  = jwt.generateToken(loginDto.getEmail());
			
			HashMap<String, Object> hm = new HashMap<>();
			
			hm.put("data", projects);
			hm.put("token", token); 
			
			return ResponseEntity.ok(hm); // all projects
		}
	}
}
