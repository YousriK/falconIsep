package com.falcon.avisep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.falcon.avisep.model.Evaluation;
import com.falcon.avisep.model.Question;
import com.falcon.avisep.repository.QuestionRepository;
@Service
public class QuestionService {
	@Autowired
	private QuestionRepository question;
	public List<Question> saveQuestion(List<Question> questions){
		for(int i=0;i<questions.size();i++){
			Evaluation ev=new Evaluation();
			Question q=questions.get(i);
			ev.setIsYesOrNot(false);
			q.setEvaluation(ev);
			question.save(questions.get(i));
		}
		return questions;
	}
}
