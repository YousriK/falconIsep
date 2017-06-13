package com.falcon.avisep.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.falcon.avisep.model.Cours;
import com.falcon.avisep.model.Evaluation;
import com.falcon.avisep.model.Module;
import com.falcon.avisep.model.UserAvis;
import com.falcon.avisep.repository.CoursRepository;
import com.falcon.avisep.repository.EvaluationRepository;
import com.falcon.avisep.repository.ModuleRepository;
import com.falcon.avisep.repository.UserAvisRepository;
import com.falcon.avisep.util.utilMethod;

@Service
public class StudentService {
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	UserAvisRepository userRepository;
	@Autowired
	CoursRepository coursRepository;
	@Autowired
	EvaluationRepository evaluationRepository;
	public List<Cours> findAllCoursByIdModule(Long id){
		List<Cours> cours=utilMethod.toList(moduleRepository.getOne(id).getCours());
		return cours;
	}
	public List<Cours> findAllCoursByModuleId(Long module_id){
		List<Cours> cours=utilMethod.toList(coursRepository.findAllCoursByModuleId(module_id));
		return !cours.isEmpty()?cours:new ArrayList<Cours>();
	}
	public List<Module> findAllModulesByIdUser(Long id){
		UserAvis user=userRepository.getOne(id);
		List<Module> modules=utilMethod.toList(user.getModule());
		return modules;
	}
	public List<Module> findAllModulesWithoutForm(){
		List<Module> modules=utilMethod.toList(moduleRepository.findAll());
		return modules;
	}
	public List<Module> findAllModulesWithForm(){
		List<Module> modules=utilMethod.toList(moduleRepository.findAllModulesWithForm());
		return !modules.isEmpty()?modules:new ArrayList<Module>();
	}
	public List<Cours> findAllEvaluatedsByUser(Long id){
		List<Cours> evaluations=utilMethod.toList(coursRepository.findAll());
		List<Cours> newEval=new ArrayList<Cours>();
		for(int i=0;i<evaluations.size();i++){
			if(evaluations.get(i).getEvaluaters().contains(id)){
				newEval.add(evaluations.get(i));
				i++;
			}
		}
		return newEval.isEmpty()?null:newEval;
	}
	public List<Cours> findAllAvailableByUser(Long id,List<Cours> evaluations){
		List<Cours> newEval=new ArrayList<Cours>();
		for(int i=0;i<evaluations.size();i++){
			if(evaluations.get(i) !=null && 
					evaluations.get(i).getForm()!=null && 
					!evaluations.get(i).getEvaluaters().contains(id))
				newEval.add(evaluations.get(i));
		}

		return newEval != null?newEval:null;
	}
}
