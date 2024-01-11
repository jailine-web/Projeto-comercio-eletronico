package com.estudos.dscommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estudos.dscommerce.entities.Role;
import com.estudos.dscommerce.entities.User;
import com.estudos.dscommerce.projections.UserDetailsProjection;
import com.estudos.dscommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

	
		@Autowired
		private UserRepository userRepository;
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);
			
			if(result.size() == 0) {
				throw new UsernameNotFoundException("User not found");
			}
			
			User user = new User();
			
			user.setEmail(username);
			user.setPassword(result.get(0).getPassword());
			
			for(UserDetailsProjection projection : result) {
				user.addRoles(new Role (projection.getRoleId(), projection.getAuthority()));
			}
			
			return user;
		
	}

}
