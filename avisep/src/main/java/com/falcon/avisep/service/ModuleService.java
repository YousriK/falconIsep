package com.falcon.avisep.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.falcon.avisep.model.Cours;
import com.falcon.avisep.model.Module;
import com.falcon.avisep.model.UserAvis;
import com.falcon.avisep.repository.CoursRepository;
import com.falcon.avisep.repository.FormRepository;
import com.falcon.avisep.repository.ModuleRepository;
import com.falcon.avisep.repository.QuestionRepository;
import com.falcon.avisep.repository.UserAvisRepository;
import com.falcon.avisep.util.utilMethod;
@Service
public class ModuleService {
	@Autowired
	private FormRepository formRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	CoursRepository coursRepository;
	@Autowired
	private UserAvisRepository userRepository;
	
	@Autowired
	private QuestionRepository question;
	
	public Module findModuleById(Long id){
		return moduleRepository.findOne(id);
	}
	public List<Cours> findAllCoursByIdModule(Long id){
		List<Cours> cours=utilMethod.toList(moduleRepository.getOne(id).getCours());
		return cours;
	}
	public List<Module> findAllModulesByIdUser(Long id){
		UserAvis user=userRepository.getOne(id);
		List<Module> modules=utilMethod.toList(user.getModule());
		return modules;
	}
	public List<Long> getAllIdModules(Long id){
		UserAvis user=userRepository.getOne(id);
		List<Module> modules=utilMethod.toList(user.getModule());
		List<Long> idList=new ArrayList<>();
		for(int i=0;i<modules.size();i++){
			idList.add(modules.get(i).getId());
		}
		return idList;
	}
}
