package com.estudos.dscommerce.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;

import com.estudos.dscommerce.entities.User;


public class UserDTO {

	private Long id;
	private String name;
	private String email;
	private String phone;
	private LocalDate birthDate;
	
	private List<String> roles = new ArrayList<>();
	
	public UserDTO(User entity) {
		id    = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		phone = entity.getPhone();
		birthDate = entity.getBirthDate();
		
		for(GrantedAuthority role: entity.getRoles()) {
			roles.add(role.getAuthority());
		}
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public List<String> getRoles() {
		return roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(email, other.email);
	}

	
}
