package com.falcon.avisep.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.falcon.avisep.model.Cours;
import com.falcon.avisep.model.Evaluation;
import com.falcon.avisep.model.Question;
import com.falcon.avisep.model.UserAvis;
import com.falcon.avisep.repository.CoursRepository;
import com.falcon.avisep.repository.EvaluationRepository;
import com.falcon.avisep.repository.QuestionRepository;
import com.falcon.avisep.repository.UserAvisRepository;

@Service
public class EvaluationService {
	@Autowired
	EvaluationRepository evaluationRepository;
	CoursService coursService;
	@Autowired
	CoursRepository coursRepo;
	@Autowired
	QuestionService questionService;
	@Autowired
	private QuestionRepository questionRepo;
	@Autowired
	private UserAvisRepository userRepository;
	@SuppressWarnings("unchecked")
	public void saveEvaluation( Long userId, List<String> keysList, List<Object> valuesList, Long id) {
		UserAvis userPrincipal =userRepository.getOne(userId);
		
		
		for(int i=0;i<keysList.size();i++){
			System.out.println(keysList.get(i).charAt(0));
			String s=String.valueOf(keysList.get(i).charAt(0));
			Long idQestion=Long.valueOf(s);
			System.out.println(idQestion);
			Question question=questionRepo.getOne(idQestion);
			Evaluation evaluation=question.getEvaluation();
			System.out.println(question.getqTitle());

			if(!question.getQType().equalsIgnoreCase("yn")){
				System.out.println("valuesList.get(i).toString()="+valuesList.get(i).toString());
				evaluation.setEData(valuesList.get(i).toString());
				evaluation.setIsYesOrNot(false);
			}else{
				System.out.println("valuesList.get(i)="+valuesList.get(i));

				if(valuesList.get(i).toString().equalsIgnoreCase("true")){
					evaluation.setIsYesOrNot(true);
				}else{evaluation.setIsYesOrNot(false);}

			}
			Date myEData=new Date();
			evaluation.setEDate(myEData);
			
			System.out.println(userPrincipal.getLastName());
			System.out.println(evaluation.getIsYesOrNot());
			System.out.println(evaluation.getEDate().toString());
			System.out.println(evaluation.getQuestion().getqTitle());

			evaluation.setOwnerId(userPrincipal.getId());
			userRepository.save(userPrincipal);
			question.setEvaluation(evaluation);
			questionRepo.save(question);
		}
	}

}
