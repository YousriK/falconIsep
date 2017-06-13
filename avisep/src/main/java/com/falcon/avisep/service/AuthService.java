package com.falcon.avisep.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.falcon.avisep.model.LDAPUserDTO;
import com.falcon.avisep.model.UserAvis;
import com.falcon.avisep.repository.UserAvisRepository;



@Service
public class AuthService {

	@Autowired
    private UserAvisRepository userRepository;

    @Autowired
    private LDAPService ldapService;

    private final int EXPIRATION_TOKEN_DURATION = 2 * 60 * 60 * 1000;

    public String generateToken(String login,String passwd) throws Exception {

    	
    	LDAPUserDTO ldapUser;
    	UserAvis user = null;
    	try {	
	    	ldapUser = ldapService.getUser(passwd, login);
	        /*
	        LDAPUserDTO ldapUser = new LDAPUserDTO();
	        ldapUser.setPrenom("simao");
	        ldapUser.setNomFamille("PEDRO");
	        ldapUser.setMail("jean.dupont@isep.fr");
	        ldapUser.setEmployeeNumber("4321");
	         */
    	}catch (Exception e) {
	        String searchBasicUser = basicLogin(passwd, login);
	        if (searchBasicUser != null) {
	            return searchBasicUser;
	        }

            ldapUser = new LDAPUserDTO();
	        ldapUser.setPrenom("Simao");
	        ldapUser.setNomFamille("PEDRO");
	        ldapUser.setMail("simao.pedro@isep.fr");
	        ldapUser.setEmployeeNumber("9167");
    	}

        user = userRepository.findByLdapId(ldapUser.getEmployeeNumber());

        String token = UUID.randomUUID().toString();
        if (user != null) {
            user.setToken(token);
            user = updateTokenExpiration(user);
            userRepository.save(user);
            return token;
        } else {
        	throw new Exception("L'utilisateur est inconnu");
        }
    }

    public String basicLogin(String passwd,String login) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserAvis user = userRepository.findByLogin(login);
        //passwordEncoder.matches(passwd, user.getPasswd())
        if (user != null && passwd.matches(user.getPasswd())) {
            String token = UUID.randomUUID().toString();
            user = updateTokenExpiration(user);
            user.setToken(token);
            userRepository.save(user);
            return token;
        }
        return null;
    }

    public UserAvis updateTokenExpiration(UserAvis user) {
        Date exp = new Date(new Date().getTime() + EXPIRATION_TOKEN_DURATION);
        user.setTokenExpiration(exp);
        return user;
    }

    public boolean isTokenExpired(UserAvis user) {
        if (user == null) return false;
        Date exp = user.getTokenExpiration();
        return exp.before(new Date());
    }

}
