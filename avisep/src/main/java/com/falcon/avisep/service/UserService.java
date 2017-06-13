package com.falcon.avisep.service;

import com.falcon.avisep.model.Role;
import com.falcon.avisep.model.Student;
import com.falcon.avisep.model.UserAvis;

public interface UserService {
	void save(Student user);
	UserAvis findByLogin(String login, String passwd, Role role);
	UserAvis findByLogin(String login, String passwd);
	UserAvis findByLogin(String login);
}
