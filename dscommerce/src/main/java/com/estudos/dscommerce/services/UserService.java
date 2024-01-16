package com.estudos.dscommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudos.dscommerce.dto.UserDTO;
import com.estudos.dscommerce.entities.Role;
import com.estudos.dscommerce.entities.User;
import com.estudos.dscommerce.projections.UserDetailsProjection;
import com.estudos.dscommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);

		if (result.size() == 0) {
			throw new UsernameNotFoundException("User not found");
		}

		User user = new User();

		user.setEmail(username);
		user.setPassword(result.get(0).getPassword());

		for (UserDetailsProjection projection : result) {
			user.addRoles(new Role(projection.getRoleId(), projection.getAuthority()));
		}

		return user;

	}
	
	protected User authenticated() {
		
		try {
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwtPrincipal = (Jwt)authentication.getPrincipal();
			String username = jwtPrincipal.getClaim("username");
			
			 //User user = userRepository.findByEmail(username).get();
			 return userRepository.findByEmail(username).get();
		}
		
		catch(Exception e) {
			throw new UsernameNotFoundException("User not found");
		}
	}
	
	@Transactional(readOnly = true)
	public UserDTO getMe() {
		
		User user = authenticated();
		return new UserDTO(user);
	}

}
