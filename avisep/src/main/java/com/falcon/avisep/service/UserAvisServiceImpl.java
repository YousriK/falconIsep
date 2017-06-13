package com.falcon.avisep.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.falcon.avisep.model.Role;
import com.falcon.avisep.model.Student;
import com.falcon.avisep.model.UserAvis;
import com.falcon.avisep.repository.UserAvisRepository;

@Service
public class UserAvisServiceImpl implements UserService{
	@Autowired
	private UserAvisRepository userRepository;
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public void save(Student user) {
		//user.setPasswd(bCryptPasswordEncoder.encode(user.getPasswd()));
		//user.addRoleAvis(RoleAvis.ROLE_STUDENT);
		userRepository.save(user);
	}
	@Override
	public UserAvis findByLogin(String login, String passwd,Role role) {
		return  userRepository.findByLogin(login,passwd,role);
	}
	@Override
	public UserAvis findByLogin(String login, String passwd) {
		return  userRepository.findByLogin(login,passwd);
	}
	@Override
	public UserAvis findByLogin(String login) {
		return userRepository.findByLogin(login);
	}
	public int userExist(String login) {
		return userRepository.exist(login);
	}
	public UserAvis findByToken(String token) {
		return userRepository.findByToken(token);
	}
//	public List<UserAvis> searchUser(Role role, String search) {    	
//    	return userRepository.searchUser(role, search);
//    }
}
