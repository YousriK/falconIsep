package com.falcon.avisep.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.falcon.avisep.model.Role;
import com.falcon.avisep.model.UserAvis;

/**
 * Spring Data JPA repository for the UserAvis entity.
 */
@Repository
public interface UserAvisRepository extends JpaRepository<UserAvis,Long> {
	UserAvis findByLogin(String login,String passwd,Role role);
	UserAvis findByLogin(String login,String passwd);
	UserAvis findByLogin(String login);
	UserAvis findByLdapId(String ldapId);
	UserAvis findByToken(String token);
	@Query("select role from UserAvis user where user.role =:role")
	Role findByRole(@Param("role") Role role);
	
	@Query("select count(login) from UserAvis user where user.login =:login")
	int exist(@Param("login")String login);
	
//	 @Query("FROM User "
//	            + "WHERE role = ?1 "
//	            + "AND (first_name like %?2% "
//	            + "OR last_name like %?2%)")
//	 List<UserAvis> searchUser(Role role, String search);
}
