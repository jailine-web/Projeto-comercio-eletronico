package com.estudos.dscommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.dscommerce.dto.UserDTO;
import com.estudos.dscommerce.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@GetMapping(value = "/me")
	public ResponseEntity<UserDTO> getMe() {
		 
		UserDTO dto = userService.getMe();
		return ResponseEntity.ok(dto);
	}
	
	
}
