package com.estudos.dscommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.estudos.dscommerce.entities.User;
import com.estudos.dscommerce.projections.UserDetailsProjection;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query(nativeQuery = true, value = """
			SELECT tb_users.email AS username, tb_users.password, 
			tb_roles.id AS roleId , tb_roles.authority 
			FROM tb_users
			INNER JOIN tb_user_roles ON tb_users.id = tb_user_roles.user_id
			INNER JOIN tb_roles ON tb_roles.id = tb_user_roles.roles_id
			WHERE tb_users.email = :email
			""")
	List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
	
}
